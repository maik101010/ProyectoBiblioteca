package com.biblioteca.biblioteca.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;

@Service
public class ServiceLibro {
	@Autowired RepositoryLibro repositoryLibro;
	
	public ServiceLibro(RepositoryLibro nombreLibro) {
		this.repositoryLibro = nombreLibro;
	}
	
	
	public Libro insertarLibro(String codigoIsbn, String nombreLibro) {
		Libro entity = new Libro();
		entity.setCodigoIsbn(codigoIsbn);
		entity.setNombreLibro(nombreLibro);
		entity.setCantidad(1);
		repositoryLibro.save(entity);
		return entity;
	}

	public Libro findByCodigoIsbn(String codigoIsbn) {
		// TODO Auto-generated method stub
		return repositoryLibro.findByCodigoIsbn(codigoIsbn);
	}

	public void actualizarLibro(Libro libroRetornado) {
		repositoryLibro.save(libroRetornado);		
	}

	public List<Libro> findAll() {
		// TODO Auto-generated method stub
		return repositoryLibro.findAll();
	}
	
	public Libro validarEjemplarInventario(String codigoIsbn) {
		Libro libro = repositoryLibro.findByCodigoIsbn(codigoIsbn);
		return libro;
	}

//	public boolean existsByCodigoIsbn(String codigoIsbn) {
//		// TODO Auto-generated method stub
//		return repositoryLibro.e;
//	}

	

}
