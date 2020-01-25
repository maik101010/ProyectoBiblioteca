package com.biblioteca.biblioteca.controller;


import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.negocio.ProcesoLibro;
import com.biblioteca.biblioteca.service.ServiceLibro;

@RestController
@CrossOrigin
@RequestMapping(path = ("/api"))
public class ControllerLibro {
	private final Logger log = LoggerFactory.getLogger(ControllerLibro.class);

	@Autowired
	private ServiceLibro serviceLibro;
	
	@Autowired
	private ProcesoLibro procesoLibro;
	
	@PostMapping(path = "/libro/add")
	ResponseEntity<Libro> crearLibro(@Valid @RequestBody String codigoIsbn, String nombreLibro) throws URISyntaxException {
		Libro libroRetornado = procesoLibro.validarEjemplarInventario(codigoIsbn);
		if(libroRetornado!=null) {
			serviceLibro.actualizarLibro(libroRetornado);
		}
		serviceLibro.insertarLibro(codigoIsbn, nombreLibro);
//		Libro result = examenRepository.save(Libro);
		return ResponseEntity.ok().body(libroRetornado);
	}
}
