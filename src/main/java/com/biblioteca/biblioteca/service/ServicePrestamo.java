package com.biblioteca.biblioteca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.dto.PrestamoDto;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.entity.Prestamo;
import com.biblioteca.biblioteca.exception.*;
import com.biblioteca.biblioteca.interfaces.RepositoryPrestamo;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;

@Service
public class ServicePrestamo {
	@Autowired
	private RepositoryPrestamo repositoryPrestamo;
	@Autowired
	private ProcesoLogicaNegocio logicaNegocio;
	@Autowired
	private ServiceLibro serviceLibro;

	public boolean insertarPrestamo(PrestamoDto prestamoDto) {
		Prestamo entity = new Prestamo();
		Libro libro = null;
		boolean registrar = false;
		if (logicaNegocio.palindromo(prestamoDto.getCodigoIsbn())) {
			new ExepcionPalindromo("los libros palíndromos solo se pueden utilizar en la biblioteca");
		} else {
			entity.setNombreSolicitante(prestamoDto.getNombrePersona());
			libro = serviceLibro.validarEjemplarInventario(prestamoDto.getCodigoIsbn());
			if (libro != null) {
				registrar = true;
				int cantidad = libro.getCantidad();
				libro.setCantidad(--cantidad);
				serviceLibro.actualizarLibro(libro);
			} else {
				new ExepcionLibroPrestado("El libro con el codigo " + prestamoDto.getCodigoIsbn() + " no existe");
			}
		}
		Date fechaMaximaEntrega = logicaNegocio.calcularfechaEntregaMaxima(prestamoDto.getFechaSolicitud(), prestamoDto.getCodigoIsbn(), 15);
		
		if (registrar) {
			entity.setLibro(libro);
			entity.setEstadoPrestamo(true);
			entity.setFechaEntrega(fechaMaximaEntrega);
			repositoryPrestamo.save(entity);
		}

		return registrar;
	}

}
