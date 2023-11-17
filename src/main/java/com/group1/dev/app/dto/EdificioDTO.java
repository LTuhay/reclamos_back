package com.group1.dev.app.dto;

import java.util.List;
import java.util.Map;

public record EdificioDTO(
		
		int id,
		String direccion,
		List<UnidadDTO> unidades
		
		) {
	
	
	public Map<String, Object> toMap() {
	    return Map.ofEntries(
	      Map.entry("id", id),
	      Map.entry("direccion", direccion),
	      Map.entry("unidades", unidades)
	    );
	  }
}
