package com.group1.dev.app.dto;

import java.util.Map;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.TipoReclamo;



public record ReclamoDTO(String titulo, EntityUser user, String descripcion, EstadoReclamo estadoReclamo,
		TipoReclamo tipoReclamo, String actualizacion, Edificio edificio

) {

	public Map<String, Object> toMap() {
		return Map.of("titulo", titulo, "descripcion", descripcion, "estadoReclamo", estadoReclamo.name(),
				"tipoReclamo", tipoReclamo.name(), "edificio", edificio, "user",user, "actualizacion", actualizacion);
	}

}
