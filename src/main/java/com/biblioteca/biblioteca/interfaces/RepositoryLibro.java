package com.biblioteca.biblioteca.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.entity.Libro;
import java.lang.String;
import java.util.List;

public interface RepositoryLibro extends JpaRepository<Libro, Integer> {
	/*Agregar Libros:
Para agregar libros en la biblioteca se deberá añadir el ISBN y el nombre de libros, si el
libro ya existe añade un ejemplar más al inventario.*/
	boolean agregarLibro(String codigoIsbn, String nombreLibro);
	boolean eliminarLibro(String codigoIsbn);
	Libro findByCodigoIsbn(String codigoisbn);
//	Libro finLibro(int id);

}
