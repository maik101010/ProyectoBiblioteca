package com.biblioteca.biblioteca.dto;

import java.util.Date;

public class PrestamoDto {
	private String nombrePersona;
	private String codigoIsbn;
	private Date fechaSolicitud;
	private Date fechaEntrega;
	
	
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(final String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getCodigoIsbn() {
		return codigoIsbn;
	}
	public void setCodigoIsbn(final String codigoIsbf) {
		this.codigoIsbn = codigoIsbf;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
		
}
