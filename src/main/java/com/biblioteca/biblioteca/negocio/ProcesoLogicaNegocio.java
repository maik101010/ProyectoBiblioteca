package com.biblioteca.biblioteca.negocio;


import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class ProcesoLogicaNegocio {

	public boolean palindromo(String codigoIsbn) {
		return codigoIsbn.equals(new StringBuilder(codigoIsbn).reverse().toString());
	}

	public int sumarDigitosIsbn(String isbn) {

		int sumaDigitos = 0;
		if (isbn != null && !isbn.isEmpty()) {
			String digitos = isbn.replaceAll("\\D+", "");
			sumaDigitos = digitos.chars().map(digit -> Character.getNumericValue(digit)).sum();
		}

		return sumaDigitos;
	}

	public Date calcularfechaEntregaMaxima(Date fechaSolicitud, String isbn, int dias) {
		if (sumarDigitosIsbn(isbn) > 30) {
			Calendar fechaEntrega = Calendar.getInstance();
			fechaEntrega.setTime(fechaSolicitud);
			int diasSumados = 0;
			while (diasSumados < (dias - 1)) {
				fechaEntrega.add(Calendar.DAY_OF_MONTH, 1);
				if (fechaEntrega.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
					++diasSumados;
				}
			}
			return (Date) fechaEntrega.getTime();
		} else {
			return null;
		}
	}

}
