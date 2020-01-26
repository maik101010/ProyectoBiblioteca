package com.biblioteca.biblioteca.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

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

import com.biblioteca.biblioteca.dto.LibroDto;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.helper.LibroHelper;
import com.biblioteca.biblioteca.service.ServiceLibro;

@RestController
@CrossOrigin
@RequestMapping(path = ("/api"))
public class ControllerLibro {

	@Autowired
	private ServiceLibro serviceLibro;

	@PostMapping(path = "/libro/add")
	ResponseEntity<Libro> crearLibro(@Valid @RequestBody LibroDto libroDto) throws URISyntaxException {
		Libro libroRetornado;
		libroRetornado = serviceLibro.validarEjemplarInventario(libroDto.getCodigoIsbn());
		if (libroRetornado != null) {
			int cantidad = libroRetornado.getCantidad();
			libroRetornado.setCantidad(++cantidad);
			serviceLibro.actualizarLibro(libroRetornado);
		} else {
			libroRetornado = serviceLibro.insertarLibro(libroDto.getCodigoIsbn(), libroDto.getNombreLibro());
		}
		return ResponseEntity.ok().body(libroRetornado);
	}

	@DeleteMapping(path = "/libro/delete/{codigo}")
	public ResponseEntity<Libro> deleteLibro(@PathVariable(name = "codigo") String codigoIsbn) {
		Libro libroRetornado;
		libroRetornado = serviceLibro.validarEjemplarInventario(codigoIsbn);
		if (libroRetornado != null) {
			int cantidad = libroRetornado.getCantidad();
			if (cantidad != 0) {
				libroRetornado.setCantidad(--cantidad);
				serviceLibro.actualizarLibro(libroRetornado);
				return ResponseEntity.ok().body(libroRetornado);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping(path = "/libro/get")
	List<LibroDto> getLibro() throws URISyntaxException {
		List<Libro> libroRetornado = serviceLibro.findAll();
		return LibroHelper.toListLevel1DTO(libroRetornado);
	}

}
