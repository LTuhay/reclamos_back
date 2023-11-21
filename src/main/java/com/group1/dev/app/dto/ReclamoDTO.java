package com.group1.dev.app.dto;

import java.util.Map;

import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.TipoReclamo;

public record ReclamoDTO(Integer reclamo_id, String titulo, int userid, String descripcion, EstadoReclamo estadoReclamo,
		TipoReclamo tipoReclamo, String actualizacion, int edificioid

) {

	public Map<String, Object> toMap() {
		return Map.of("reclamo_id", reclamo_id, "titulo", titulo, "descripcion", descripcion, "estadoReclamo",
				estadoReclamo.name(), "tipoReclamo", tipoReclamo.name(), "edificioid", edificioid, "userid", userid,
				"actualizacion", actualizacion);
	}

}
