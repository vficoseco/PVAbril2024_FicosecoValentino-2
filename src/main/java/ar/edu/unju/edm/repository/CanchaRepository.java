package ar.edu.unju.edm.repository;

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.repository.CanchaRepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends CrudRepository <Cancha, Integer>{
	public Optional<Cancha> findBycodigo (Integer codigo);
}