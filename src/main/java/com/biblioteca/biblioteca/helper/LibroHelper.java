package com.biblioteca.biblioteca.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.biblioteca.biblioteca.dto.LibroDto;
import com.biblioteca.biblioteca.entity.Libro;

public class LibroHelper {

	public static LibroDto toLevel1DTO(Libro libro) {
		LibroDto dto = new LibroDto();
		dto.setCodigoIsbn(libro.getCodigoIsbn());
		dto.setNombreLibro(libro.getNombreLibro());
		if (StringUtils.isNotBlank(String.valueOf(libro.getCantidad()))) {
			dto.setCantidad(libro.getCantidad());
		}
		return dto;
	}
	
	public static List<LibroDto> toListLevel1DTO(List<Libro> list) {
		List<LibroDto> listDto = new ArrayList<>();
		for(Libro libro : list ) {
			listDto.add(toLevel1DTO(libro));
		}
		return listDto;
	}	
}
