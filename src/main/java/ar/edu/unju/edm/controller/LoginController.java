package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.ICanchaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class LoginController {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    ICanchaService canchaService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("dni") Integer dni, @RequestParam("password") String password, Model model) {
        Usuario usuario = usuarioService.listarUnUsuario(dni);
        if (usuario != null && usuario.getPassword().equals(password)) {
            if (usuario.getTipoUsuario() == 'A') {
                return "redirect:/admin/principal";
            } else if (usuario.getTipoUsuario() == 'V') {
                return "redirect:/usuario/principal";
            }
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }
}
