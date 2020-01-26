package com.biblioteca.biblioteca.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.dto.PrestamoDto;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.entity.Prestamo;
import com.biblioteca.biblioteca.exception.*;
import com.biblioteca.biblioteca.helper.PrestamoHelper;
import com.biblioteca.biblioteca.interfaces.RepositoryPrestamo;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;

@Service
public class ServicePrestamo {
	@Autowired
	RepositoryPrestamo repositoryPrestamo;
	@Autowired
	ProcesoLogicaNegocio logicaNegocio;
	@Autowired
	ServiceLibro serviceLibro;

	public void insertarPrestamo(PrestamoDto prestamoDto) throws ExepcionPalindromo, ExepcionLibroPrestado {
		Prestamo entity = new Prestamo();
		Libro libro = null;
		boolean registrar = false;
		if (logicaNegocio.palindromo(prestamoDto.getCodigoIsbn())) {
			throw new  ExepcionPalindromo("los libros palíndromos solo se pueden utilizar en la biblioteca");
		} else {			
			libro = logicaNegocio.validarEjemplarInventario(prestamoDto.getCodigoIsbn());
			if (libro != null && libro.getCantidad() >0) {
				registrar = true;
				int cantidad = libro.getCantidad();
				libro.setCantidad(--cantidad);
				serviceLibro.actualizarLibro(libro);
			} else {
				throw new ExepcionLibroPrestado("El libro con el codigo " + prestamoDto.getCodigoIsbn() + " no tiene "
						+ "unidades disponibles");
			}
		}
		
		Date fechaSolicitud = new Date();
		Date fechaMaximaEntrega = logicaNegocio.calcularfechaEntregaMaxima(fechaSolicitud, prestamoDto.getCodigoIsbn(), 15);
		
		if (registrar) {
			entity.setNombreSolicitante(prestamoDto.getNombrePersona());
			entity.setLibro(libro);
			entity.setEstadoPrestamo(true);
			entity.setFechaSolicitud(fechaSolicitud);
			entity.setFechaEntrega(fechaMaximaEntrega);
			repositoryPrestamo.save(entity);
		}		
	}
	
	
	public List<Prestamo> findAll() {		
		return repositoryPrestamo.findAll();
	}

}
