package com.biblioteca.biblioteca.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.service.ServiceLibro;

@Component
public class ProcesoLibro {

	@Autowired
	private ServiceLibro serviceLibro;

	public Libro validarEjemplarInventario(String codigoIsbn) {
		Libro libro = serviceLibro.findByCodigoIsbn(codigoIsbn);
		if (libro!=null) {
			int cantidad = libro.getCantidad();
			libro.setCantidad(cantidad++);
		}
		return libro;
	}

}
