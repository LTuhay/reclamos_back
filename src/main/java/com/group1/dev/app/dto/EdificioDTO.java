package com.group1.dev.app.dto;

import java.util.List;

public record EdificioDTO(
		
		String direccion,
		List<UnidadDTO> unidades
		
		) {

}
