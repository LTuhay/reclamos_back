package com.group1.dev.app.dto;

import java.util.List;
import java.util.Map;

import com.group1.dev.app.model.entity.EntityUser;

public record UnidadDTO(
		
		int id,
		String nro,
	    String piso,
	    String estado,
	    Integer edificioID,
		List<UserDTO> personas
		
		) {
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = Map.ofEntries(
			    Map.entry("id", id),
	            Map.entry("nro", nro),
	            Map.entry("piso", piso),
	            Map.entry("estado", estado),
	            Map.entry("edificioID", edificioID)
	        );

	        if (personas != null) {
	            map.put("personas", personas);
	        }

	        return map;
	      
	  }
	

}