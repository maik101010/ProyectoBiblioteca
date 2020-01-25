package com.biblioteca.biblioteca.negocio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biblioteca.biblioteca.controller.ControllerLibro;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.service.ServiceLibro;

@Component
public class ProcesoLibro {

	@Autowired
	private ServiceLibro serviceLibro;
	private final Logger log = LoggerFactory.getLogger(ProcesoLibro.class);

	public Libro validarEjemplarInventario(String codigoIsbn) {
		Libro libro = serviceLibro.findByCodigoIsbn(codigoIsbn);
		
		return libro;
	}

}
