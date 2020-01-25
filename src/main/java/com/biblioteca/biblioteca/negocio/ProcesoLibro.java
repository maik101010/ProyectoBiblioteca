package com.biblioteca.biblioteca.negocio;

import java.sql.Date;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biblioteca.biblioteca.controller.ControllerLibro;
import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.service.ServiceLibro;

@Component
public class ProcesoLibro {

	@Autowired
	private ServiceLibro serviceLibro;
	private final Logger log = LoggerFactory.getLogger(ProcesoLibro.class);

	public Libro validarEjemplarInventario(String codigoIsbn) {
		Libro libro = serviceLibro.findByCodigoIsbn(codigoIsbn);
		
		return libro;
	}
	public boolean palindromo(String codigoIsbn) {
		int inicial=0;
		int fin=codigoIsbn.length()-1;
		boolean respuesta=false;
		while((inicial<fin) && (!respuesta)) {
			if(codigoIsbn.charAt(inicial)==codigoIsbn.charAt(fin)) {
				inicial++;
				fin--;
			}else {
				respuesta=true;
			}	
		}
		return  respuesta;
	}
	
    public int sumarDigitosIsbn(String isbn){
		
		int sumaDigitos = 0;		
		if(isbn != null && !isbn.isEmpty()){
			String digitos = isbn.replaceAll("\\D+","");
			sumaDigitos = digitos.chars()
			        .map(digit -> Character.getNumericValue(digit))
			        .sum();
		}
		
		return sumaDigitos;
}

	public Date calcularfechaEntregaMaxima(Date fechaSolicitud, String isbn, int dias){							
		if(sumarDigitosIsbn(isbn)>30){					
			Calendar fechaEntrega = Calendar.getInstance();
			fechaEntrega.setTime(fechaSolicitud);
		    int diasSumados = 0; 
			while (diasSumados < (dias-1)) {
				fechaEntrega.add(Calendar.DAY_OF_MONTH, 1);
		        if (fechaEntrega.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		            ++diasSumados;
		        }
		    }
			return (Date) fechaEntrega.getTime();
		}else{
			return null;
		}				
}
    

}
