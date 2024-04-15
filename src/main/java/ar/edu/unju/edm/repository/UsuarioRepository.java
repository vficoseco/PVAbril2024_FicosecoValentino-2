package ar.edu.unju.edm.repository;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, Integer>{
	public Optional<Usuario> findBydni (Integer dni);
}