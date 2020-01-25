package com.biblioteca.biblioteca.controller;

import java.net.URISyntaxException;
import java.util.Date;
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

import com.biblioteca.biblioteca.dto.PrestamoDto;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.entity.Prestamo;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;
import com.biblioteca.biblioteca.service.ServiceLibro;
import com.biblioteca.biblioteca.service.ServicePrestamo;

@RestController
@CrossOrigin
@RequestMapping(path = ("/api"))
public class ControllerPrestamo {
//	private final Logger log = LoggerFactory.getLogger(ControllerPrestamo.class);

	@Autowired
	private ServicePrestamo servicePrestamo;

//	@Autowired
//	private ProcesoLibro procesoLibro;

	@PostMapping(path = "/prestamo/add")
	String crearLibro(@Valid @RequestBody PrestamoDto prestamoDto) throws URISyntaxException {
		String response = null;
		prestamoDto.setFechaSolicitud(new Date());
		if (servicePrestamo.insertarPrestamo(prestamoDto)) {
			response = "Registrado";
		}else {
			response = "No registrado";
		}
		return response;

	}

}
