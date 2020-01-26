package com.biblioteca.biblioteca.helper;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.biblioteca.dto.PrestamoDto;
import com.biblioteca.biblioteca.entity.Prestamo;

public class PrestamoHelper {

	public static PrestamoDto toLevel1DTO(Prestamo prestamo) {
		PrestamoDto dto = new PrestamoDto();
		dto.setCodigoIsbn(prestamo.getLibro().getCodigoIsbn());
		dto.setNombrePersona(prestamo.getNombreSolicitante());
		dto.setFechaSolicitud(prestamo.getFechaSolicitud());
		dto.setFechaEntrega(prestamo.getFechaEntrega());
		return dto;
	}
	
	
	public static List<PrestamoDto> toListLevel1DTO(List<Prestamo> list) {
		List<PrestamoDto> listDto = new ArrayList<>();
		for(Prestamo prestamo : list ) {
			listDto.add(toLevel1DTO(prestamo));
		}
		return listDto;
	}	
}
