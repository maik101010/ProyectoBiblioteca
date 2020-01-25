package com.biblioteca.biblioteca.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca.entity.Libro;
import com.biblioteca.biblioteca.entity.Prestamo;

import java.lang.String;
import java.util.List;
@Repository
public interface RepositoryPrestamo extends JpaRepository<Prestamo, Integer> {

}
