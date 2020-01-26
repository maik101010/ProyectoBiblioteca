package com.biblioteca.biblioteca.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca.entity.Libro;
import java.lang.String;
@Repository
public interface RepositoryLibro extends JpaRepository<Libro, Integer> {
	Libro findByCodigoIsbn(String codigoisbn);

}
