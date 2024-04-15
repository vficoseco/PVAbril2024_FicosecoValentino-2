package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cancha;

@Service
public interface ICanchaService {
	public void cargarCancha(Cancha unaCancha);
	public void eliminarCancha(Integer Codigo);
	public ArrayList<Cancha> listarCanchasDeFutbol ();
	public Cancha listarUnaCancha(Integer Codigo);
	public Cancha modificarUnaCancha(Integer Codigo, Cancha CanchaModificada);
}