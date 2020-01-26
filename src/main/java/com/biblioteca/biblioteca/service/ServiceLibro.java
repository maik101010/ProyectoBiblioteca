package com.biblioteca.biblioteca.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;

@Service
public class ServiceLibro {
	@Autowired private RepositoryLibro repositoryLibro;
	private static final int CANTIDAD_REGISTRO =1;
	
	public ServiceLibro(RepositoryLibro nombreLibro) {
		this.repositoryLibro = nombreLibro;
	}
	
		public Libro insertarLibro(String codigoIsbn, String nombreLibro) {
		Libro entity = new Libro();
		entity.setCodigoIsbn(codigoIsbn);
		entity.setNombreLibro(nombreLibro);
		entity.setCantidad(CANTIDAD_REGISTRO);
		repositoryLibro.save(entity);
		return entity;
	}

	public Libro findByCodigoIsbn(String codigoIsbn) {
		return repositoryLibro.findByCodigoIsbn(codigoIsbn);
	}

	public void actualizarLibro(Libro libroRetornado) {
		repositoryLibro.save(libroRetornado);		
	}

	public List<Libro> findAll() {
		return repositoryLibro.findAll();
	}
	
	public Libro validarEjemplarInventario(String codigoIsbn) {
		Libro libro = repositoryLibro.findByCodigoIsbn(codigoIsbn);
		return libro;
	}

}
