package com.group1.dev.app.dto;

public record ImagenDTO(
		int id,
		String nombreImagen,
		String descripcion,
		byte[] datosImagen
		
		) {

}
