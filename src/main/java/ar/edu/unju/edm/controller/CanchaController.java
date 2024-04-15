package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.service.ICanchaService;

@Controller
public class CanchaController {
	
	private static final Log VF = LogFactory.getLog(CanchaController.class);
	
	@Autowired
	Cancha unCancha;
	
	@Autowired
	ICanchaService unServicio;
	
	@GetMapping("/Cancha")
	public ModelAndView cargarCancha() {
		ModelAndView cargaCancha = new ModelAndView("formularioCancha");
		cargaCancha.addObject("nuevaCancha", unCancha);
		
		cargaCancha.addObject("band", false);
		VF.warn("Cargando nueva Cancha");
		return cargaCancha;
	}
	@GetMapping("/Cancha/{Codigo}")
	public ModelAndView cargarCanchaCodigo(@PathVariable(name="Codigo") Integer Codigo) {
		ModelAndView listadoCanchas = new ModelAndView("perfilCancha");
		
		listadoCanchas.addObject("Cancha",unServicio.listarUnaCancha(Codigo));
		
		return listadoCanchas;
	}
	@GetMapping("/listadoCancha")
	public ModelAndView mostrarCancha() {
		ModelAndView listadoCanchas = new ModelAndView("mostrarCanchas");
		listadoCanchas.addObject("CanchaListado", unCancha);
		
		listadoCanchas.addObject("CanchaListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchas;
	}
	
	@GetMapping("/listaDeCanchas")
	public ModelAndView mostrarCanchas() {
		ModelAndView listadoCanchas = new ModelAndView("listaDeCanchas");
		listadoCanchas.addObject("CanchaListado", unCancha);
		
		listadoCanchas.addObject("CanchaListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchas;
	}
	
	@PostMapping(value = "/guardarCancha", consumes = "multipart/form-data")
public ModelAndView guardarCancha(@ModelAttribute("nuevaCancha") Cancha Canchanueva, @RequestParam("file") MultipartFile[] archivo, BindingResult resultado) {

    if (resultado.hasErrors()) {
        ModelAndView cargaCancha = new ModelAndView("formularioCancha");
        cargaCancha.addObject("nuevaCancha", Canchanueva);
        return cargaCancha;
    }
		ModelAndView listadoCanchas = new ModelAndView("mostrarCanchas");

        VF.warn("Mostrando la nueva Cancha de Futbol " + Canchanueva.getCapacidad());
    try {
        unServicio.cargarCancha(Canchanueva);
    } catch (Exception e) {
        listadoCanchas.addObject("cargaCanchaErrorMessage", e.getMessage());
        VF.error(e);
    }

    listadoCanchas.addObject("CanchaListado", unServicio.listarCanchasDeFutbol());

    return listadoCanchas;
}
	
	
	@GetMapping("/modificarCancha/{LgetCodigo}")
	public ModelAndView modificarCancha(@PathVariable(name="LgetCodigo") Integer Codigo) {
		ModelAndView editarCancha = new ModelAndView("formularioCancha");
		
		try {
			editarCancha.addObject("nuevaCancha",unServicio.listarUnaCancha(Codigo));
		}catch(Exception e) {
			editarCancha.addObject("modificarCanchaErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		VF.warn("Cancha a modificar: " + Codigo);
		editarCancha.addObject("band", true);
		
		return editarCancha;
	}
	
	@PostMapping(value ="/modificarCancha", consumes="multipart/form-data")
	public ModelAndView modificarCancha(@ModelAttribute("nuevaCancha") Cancha Canchanueva, @RequestParam("file") MultipartFile[] archivo){
		ModelAndView listadoCanchas = new ModelAndView("mostrarCanchas");
		
		try {
			VF.warn("Cancha modificado: " + Canchanueva.getCodigo());
			unServicio.cargarCancha(Canchanueva);
		}catch(Exception e) {
			listadoCanchas.addObject("cargaCanchaErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		listadoCanchas.addObject("CanchaListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchas;
	}
	
	//ELIMINAR
	
	@GetMapping("/eliminarCancha/{LgetCodigo}")
	public ModelAndView eliminarCancha(@PathVariable(name="LgetCodigo") Integer Codigo) {
		ModelAndView eliminarCancha = new ModelAndView("mostrarCanchas");
		
		try {
			VF.warn("Eliminando Cancha");
			unServicio.eliminarCancha(Codigo);
		}catch(Exception e) {
			eliminarCancha.addObject("eliminarCanchaErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		try {
			VF.warn("Listando Canchas");
			eliminarCancha.addObject("CanchaListado", unServicio.listarCanchasDeFutbol());
		}catch(Exception e) {
			eliminarCancha.addObject("listarCanchaErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		return eliminarCancha;
	}

}

