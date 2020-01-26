package com.biblioteca.integracion;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;


import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;
import com.biblioteca.biblioteca.service.ServiceLibro;

import builder.BuilderLibro;

public class LibroTest {
	private static final String EL_MITO = "El mito";
//	private static final String CODIGO_LIBRO = "ASASDA123";
	private static final String CODIGO_LIBRO_DOS = "ASASDA113";
	private static final String NOMBRE_PALINDROMO = "lateleletal";

	ServiceLibro serviceLibro = mock(ServiceLibro.class);
	RepositoryLibro repositoryLibro = mock(RepositoryLibro.class);
	ServiceLibro serviceLibroValidacion = new ServiceLibro(repositoryLibro);
	ProcesoLogicaNegocio logicaNegocio = new ProcesoLogicaNegocio();

	@Test
	public void agregarLibro() {
		Libro libro = new BuilderLibro().conNombre(EL_MITO).build();
		libro = serviceLibroValidacion.insertarLibro(libro.getCodigoIsbn(), libro.getNombreLibro());
		Assert.assertNotNull(libro);

	}

	@Test
	public void validarEjemplar() {
		Libro libro = new BuilderLibro().conNombreAndCodigo("El coronel", CODIGO_LIBRO_DOS).build();
		libro = serviceLibroValidacion.insertarLibro(libro.getCodigoIsbn(), libro.getNombreLibro());
//		when(serviceLibroValidacion.validarEjemplarInventario(libro.getCodigoIsbn())).thenReturn(libro);
		Libro libroReturn = repositoryLibro.findByCodigoIsbn(libro.getCodigoIsbn());
		Assert.assertNotNull(libroReturn);
//		Assert.assertNotNull(serviceLibroValidacion.validarEjemplarInventario(libro.getCodigoIsbn()));
	}

	@Test
	public void validaPalindromo() {
		Assert.assertTrue(logicaNegocio.palindromo(NOMBRE_PALINDROMO));
	}

}
