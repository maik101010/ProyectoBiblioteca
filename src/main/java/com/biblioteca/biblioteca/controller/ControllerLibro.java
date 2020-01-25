package com.biblioteca.biblioteca.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	ResponseEntity<Libro> crearLibro(@Valid @RequestBody Libro libro) throws URISyntaxException {
		Libro libroRetornado;
		libroRetornado = procesoLibro.validarEjemplarInventario(libro.getCodigoIsbn());
		if (libroRetornado != null) {
			serviceLibro.actualizarLibro(libroRetornado);
		} else {
			libroRetornado = serviceLibro.insertarLibro(libro.getCodigoIsbn(), libro.getNombreLibro());
		}
		return ResponseEntity.ok().body(libroRetornado);
	}
	
//	@DeleteMapping(path = "/examen/delete/{codigo}")
//	ResponseEntity<Libro> ResponseEntity<Libro> eliminarLibro(@PathVariable(name = "codigo") String codigo) {
//		return ResponseEntity.ok().body(new Libro());
//	}
	
	@DeleteMapping(path = "/libro/delete/{codigo}")
	public ResponseEntity<Libro> deleteUser(@PathVariable(name = "codigo") String codigoIsbn) {
		Libro libroRetornado;
		libroRetornado = procesoLibro.validarEjemplarInventario(codigoIsbn);
		if (libroRetornado!=null) {
			int cantidad = libroRetornado.getCantidad();
			libroRetornado.setCantidad(--cantidad);
			serviceLibro.actualizarLibro(libroRetornado);
			return ResponseEntity.ok().body(libroRetornado);
			
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@GetMapping(path = "/libro/get")
	List<Libro> getLibro() throws URISyntaxException {
		
			List<Libro>libroRetornado = serviceLibro.findAll();
		
		return libroRetornado;
	}
	
}
