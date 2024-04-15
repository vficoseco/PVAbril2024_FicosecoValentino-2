package ar.edu.unju.edm.service.imp;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.repository.CanchaRepository;
import ar.edu.unju.edm.service.ICanchaService;

@Service
public class ImpCanchaService implements ICanchaService {

    @Autowired
	public CanchaRepository CanchaRepository;

   @Override
    public void cargarCancha(Cancha unaCancha) {
	   CanchaRepository.save(unaCancha);
    }

   @Override
   public void eliminarCancha(Integer codigo) {
       Optional<Cancha> CanchaOptional = CanchaRepository.findBycodigo(codigo);
       CanchaOptional.ifPresent(CanchaRepository::delete);
   }

   @Override
   public ArrayList<Cancha> listarCanchasDeFutbol() {
       return (ArrayList<Cancha>) CanchaRepository.findAll();
   }

   @Override
   public Cancha listarUnaCancha(Integer codigo) {
       Optional<Cancha> CanchaOptional = CanchaRepository.findBycodigo(codigo);
       return CanchaOptional.orElse(null);
   }

  @Override
	public Cancha modificarUnaCancha(Integer codigo, Cancha CanchaModificado) {
		Optional<Cancha> CanchaOptional = CanchaRepository.findBycodigo(codigo);
		if (CanchaOptional.isPresent()) {
			Cancha Cancha = CanchaOptional.get();
			Cancha.setCapacidad(CanchaModificado.getCapacidad());
			Cancha.setprecio(CanchaModificado.getprecio());
			CanchaRepository.save(Cancha);
			return Cancha;
		}
		return null;
	}


}
