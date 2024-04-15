package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;

@Service
public class ImpUsuarioService implements IUsuarioService {

    @Autowired
	public UsuarioRepository UsuarioRepository;

   @Override
    public void cargarUsuario(Usuario unUsuario) {
	   UsuarioRepository.save(unUsuario);
    }

    @Override
    public void eliminarUnUsuario(Integer dni) {
        Optional<Usuario> UsuarioOptional = UsuarioRepository.findBydni(dni);
        UsuarioOptional.ifPresent(UsuarioRepository::delete);
    }

    @Override
    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) UsuarioRepository.findAll();
    }

    @Override
    public Usuario listarUnUsuario(Integer dni) {
        Optional<Usuario> UsuarioOptional = UsuarioRepository.findBydni(dni);
        return UsuarioOptional.orElse(null);
    }

   @Override
	public Usuario modificarUnUsuario(Integer dni, Usuario UsuarioModificado) {
		Optional<Usuario> UsuarioOptional = UsuarioRepository.findBydni(dni);
		if (UsuarioOptional.isPresent()) {
			Usuario Usuario = UsuarioOptional.get();
			Usuario.setNombre(UsuarioModificado.getNombre());
			Usuario.setDni(UsuarioModificado.getDni());
			UsuarioRepository.save(Usuario);
			return Usuario;
		}
		return null;
	}


}
