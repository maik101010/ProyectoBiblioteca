package com.biblioteca.biblioteca.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;

@Service
public class ServiceLibro {
	@Autowired RepositoryLibro repositoryLibro;
	
	public Libro insertarLibro(String codigoIsbn, String nombreLibro) {
		Libro entity = new Libro();
		entity.setCodigoIsbn(codigoIsbn);
		entity.setNombreLibro(nombreLibro);
		repositoryLibro.save(entity);
		return entity;
	}
	
//	public boolean eliminarLibro(String codigoIsbn) {
//		return repositoryLibro.eliminarLibro(codigoIsbn);
//	}

	public Libro findByCodigoIsbn(String codigoIsbn) {
		// TODO Auto-generated method stub
		return repositoryLibro.findByCodigoIsbn(codigoIsbn);
	}

	public void actualizarLibro(Libro libroRetornado) {
		
		repositoryLibro.save(libroRetornado);		
	}
	

}
