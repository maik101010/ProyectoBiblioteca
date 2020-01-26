package com.biblioteca.integracion;

import static org.mockito.Mockito.mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.interfaces.RepositoryLibro;
import com.biblioteca.biblioteca.negocio.ProcesoLogicaNegocio;
import com.biblioteca.biblioteca.service.ServiceLibro;

import builder.BuilderLibro;

public class PrestamoTest {
	
	private static final String CODIGO_LIBRO_MAYOR_30 = "AS9A9SDA99997";	
	private static final String CODIGO_LIBRO_MENOR_30 = "AS1234";
	private static final int NUMERO_MAXIMO_DIAS_PRESTAMO = 15;
	private static final String FORMATO_FECHA= "dd/MM/yyyy";

	ServiceLibro serviceLibro = mock(ServiceLibro.class);
	RepositoryLibro repositoryLibro = mock(RepositoryLibro.class);
	ServiceLibro serviceLibroValidacion = new ServiceLibro(repositoryLibro);
	ProcesoLogicaNegocio logicaNegocio = new ProcesoLogicaNegocio();

	@Test
	public void calculoFechaEntregaTest() {
		
		Calendar fechaSolictud = Calendar.getInstance();
		fechaSolictud.set(Calendar.DAY_OF_MONTH, 26);
		fechaSolictud.set(Calendar.MONTH, Calendar.MAY);
		fechaSolictud.set(Calendar.YEAR, 2017);
		
		Calendar fechaEntregaEsperada = Calendar.getInstance();
		fechaEntregaEsperada.set(Calendar.DAY_OF_MONTH, 12);
		fechaEntregaEsperada.set(Calendar.MONTH, Calendar.JUNE);
		fechaEntregaEsperada.set(Calendar.YEAR, 2017);
		
		// arrange
		Libro libro1 = new BuilderLibro().conCodigo(CODIGO_LIBRO_MAYOR_30).build();					
		Libro libro2 = new BuilderLibro().conCodigo(CODIGO_LIBRO_MENOR_30).build();
		
		ProcesoLogicaNegocio logicaNegocio = new ProcesoLogicaNegocio();		
		ProcesoLogicaNegocio logicaNegocioTemp1 =  Mockito.spy(logicaNegocio);
		ProcesoLogicaNegocio logicaNegocioTemp2 =  Mockito.spy(logicaNegocio);
		
		Mockito.doReturn(31).when(logicaNegocioTemp1).sumarDigitosIsbn(Mockito.any()); 								
		Mockito.doReturn(20).when(logicaNegocioTemp2).sumarDigitosIsbn(Mockito.any());
		
		Date fechaEntregaTest1 = logicaNegocioTemp1.calcularfechaEntregaMaxima(fechaSolictud.getTime(), libro1.getCodigoIsbn(), 
						NUMERO_MAXIMO_DIAS_PRESTAMO);
		
		
		Date fechaEntregaTest2 = logicaNegocioTemp2.calcularfechaEntregaMaxima(fechaSolictud.getTime(), libro2.getCodigoIsbn(), 
				NUMERO_MAXIMO_DIAS_PRESTAMO);
		
		
		//assert
		Assert.assertTrue(assertEqualDates(fechaEntregaTest1, fechaEntregaEsperada.getTime()));
		Assert.assertTrue(fechaEntregaTest2 == null);
	}
	
	private  boolean assertEqualDates(Date fecha1, Date fecha2) {
	    DateFormat formato = new SimpleDateFormat(FORMATO_FECHA);
	    String strFecha1 = formato.format(fecha1);
	    String strFecha2 = formato.format(fecha2);
	    return strFecha1.equals(strFecha2);
	}

}
