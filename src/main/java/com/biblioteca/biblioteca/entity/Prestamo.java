package com.biblioteca.biblioteca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int idPrestamo;
	
	@Column(name = "nombre_solicitante")
	private String nombreSolicitante;
	
	@Column(name = "estado_prestamo")	
	private Boolean estadoPrestamo;
	
	@Column(name = "fecha_solicitud")
	private Date fechaSolicitud;
	
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Libro libro;
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Boolean getEstadoPrestamo() {
		return estadoPrestamo;
	}
	public void setEstadoPrestamo(Boolean estado) {
		this.estadoPrestamo = estado;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(final Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(final Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
		
}
