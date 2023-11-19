package com.group1.dev.app.dto;

import java.util.List;
import java.util.Map;

import com.group1.dev.app.model.entity.EntityUser;

public record UnidadUpdateDTO(
		
		String nro,
	    String piso,
	    String estado
		
		) {
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = Map.ofEntries(
	            Map.entry("nro", nro),
	            Map.entry("piso", piso),
	            Map.entry("estado", estado)
	        );
		
	        return map;
	      
	  }
	

}