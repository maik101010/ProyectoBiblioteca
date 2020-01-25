package com.biblioteca.biblioteca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idLibro;
	@Column(name = "isbn")
	private String codigoIsbn;
	@Column(name = "nombre_libro")
	private String nombreLibro;
	@Column(name = "cantidad")
	private int cantidad;

	public String getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(String codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", codigoIsbn=" + codigoIsbn + ", nombreLibro=" + nombreLibro
				+ ", cantidad=" + cantidad + "]";
	}

	
}
