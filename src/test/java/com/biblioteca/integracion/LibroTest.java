package com.biblioteca.integracion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;
import com.biblioteca.biblioteca.service.ServiceLibro;

import builder.BuilderLibro;

public class LibroTest {
	private static final String EL_MITO = "El mito";
//	private static final String CODIGO_LIBRO = "ASASDA123";
	private static final String CODIGO_LIBRO_DOS = "ASASDA113";

//	@Autowired
//	ServiceLibro serviceLibro ;

	ServiceLibro serviceLibro = mock(ServiceLibro.class);
	RepositoryLibro repositoryLibro = mock(RepositoryLibro.class);
	ServiceLibro serviceLibroValidacion = new ServiceLibro(repositoryLibro);
	ProcesoLogicaNegocio logicaNegocio = new ProcesoLogicaNegocio();

	@org.junit.Before
	public void iniciarServicios() {
		serviceLibro = mock(ServiceLibro.class);
	}
	@Test
	public void agregarLibro() {

		// arrange
		Libro libro = new BuilderLibro().conNombre(EL_MITO).build();
		libro = serviceLibro.insertarLibro(libro.getCodigoIsbn(), libro.getNombreLibro());
		// assert
		Assert.assertNotNull(libro);

	}

	@Test
	public void validarEjemplar() {
		// arrange
		Libro libro = new BuilderLibro().conNombre(EL_MITO).build();
			
		libro = serviceLibroValidacion.insertarLibro(libro.getCodigoIsbn(), libro.getNombreLibro());
				// assert
		Assert.assertNotNull(serviceLibroValidacion.validarEjemplarInventario(libro.getCodigoIsbn()));
	}

	@Test
	public void validaPalindromo() {
		Assert.assertTrue(logicaNegocio.palindromo("lateleletal"));
	}
//
//		// arrange
//		Producto producto = new ProductoTestDataBuilder().conNombre(COMPUTADOR_LENOVO).build();
//		// GarantiaExtendida garantiaExtendida = new G
//
//		repositorioProducto.agregar(producto);
//		GarantiaExtendida garantiaExtendida = new GarantiaExtendidaTestDataBuilder().conProducto(producto).build();
//
//		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
//
//		// act
//		vendedor.generarGarantia(producto.getCodigo(), garantiaExtendida);
//		try {
//
//			vendedor.generarGarantia(producto.getCodigo(), garantiaExtendida);
//			fail();
//
//		} catch (GarantiaExtendidaException e) {
//			// assert
//			Assert.assertEquals(Vendedor.EL_PRODUCTO_TIENE_GARANTIA, e.getMessage());
//		}
//	}
//
//	@Test
//	public void fechaFinGarantiaCostoProductoMayor() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.MONTH, 07);
//		calendar.set(Calendar.DATE, 16);
//		calendar.set(Calendar.YEAR, 2018);
//		fechaSolicitud = calendar.getTime();
//
//		Calendar calendarFechaEsperada = Calendar.getInstance();
//		calendarFechaEsperada.set(Calendar.MONTH, 03);
//		calendarFechaEsperada.set(Calendar.DATE, 06);
//		calendarFechaEsperada.set(Calendar.YEAR, 2019);
//		calendarFechaEsperada.set(Calendar.MINUTE, 0);
//		calendarFechaEsperada.set(Calendar.SECOND, 0);
//		calendarFechaEsperada.set(Calendar.MILLISECOND, 0);
//
//		Date fechaEsperada = calendarFechaEsperada.getTime();
//		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
//		vendedor.calcularFechaFinGarantiaExtendida(fechaSolicitud, costoProducto);
//		Date fechaDada = vendedor.getFechaFinGarantiaExtendida();
//		int res = fechaEsperada.compareTo(fechaDada);
//		Assert.assertEquals(0, res);
//	}
//
//	@Test
//	public void fechaFinGarantiaCostoProductoMenor() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.MONTH, 07);
//		calendar.set(Calendar.DATE, 16);
//		calendar.set(Calendar.YEAR, 2018);
//		fechaSolicitud = calendar.getTime();
//
//		Calendar calendarFechaEsperada = Calendar.getInstance();
//		calendarFechaEsperada.set(Calendar.MONTH, 10);
//		calendarFechaEsperada.set(Calendar.DATE, 24);
//		calendarFechaEsperada.set(Calendar.YEAR, 2018);
//		calendarFechaEsperada.set(Calendar.MINUTE, 0);
//		calendarFechaEsperada.set(Calendar.SECOND, 0);
//		calendarFechaEsperada.set(Calendar.MILLISECOND, 0);
//		
//		Date fechaEsperada = calendarFechaEsperada.getTime();
//
//		Vendedor vendedor = new Vendedor();
//		costoProducto = false;
//		vendedor.calcularFechaFinGarantiaExtendida(fechaSolicitud, costoProducto);
//		Assert.assertEquals(fechaEsperada, vendedor.getFechaFinGarantiaExtendida());
//	}
//
//	@Test
//	public void precioProductoGarantiaMayor() {
//		Producto producto = new ProductoTestDataBuilder().conPrecio(650000).build();
//		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
//
//		vendedor.calcularPrecioGarantiaExtendida(producto, new Date());
//		Assert.assertEquals(PRECIO_ESPERADO, vendedor.getPrecioGarantiaExtendida(), 0);
//	}
//
//	@Test
//	public void precioProductoGarantiaMenor() {
//		Producto producto = new ProductoTestDataBuilder().conPrecio(450000).build();
//		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
//
//		vendedor.calcularPrecioGarantiaExtendida(producto, new Date());
//		Assert.assertEquals(PRECIO_ESPERADO_MENOR, vendedor.getPrecioGarantiaExtendida(), 0);
//		
//	}
//
//	@Test
//	public void productoNoTieneGarantiaTest() {
//		// arrange
//		Producto producto = new ProductoTestDataBuilder().conCodigo("ASASaeee").build();
//		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
//		try {
//			vendedor.validarGeneracionGarantia(producto);
//			fail();
//
//		} catch (GarantiaExtendidaException e) {
//			// assert
//			Assert.assertEquals(Vendedor.EL_PRODUCTO_NO_TIENE_GARANTIA, e.getMessage());
//		}
//	}
}
