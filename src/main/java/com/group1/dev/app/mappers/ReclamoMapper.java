package com.group1.dev.app.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.ReclamoDTO;
import com.group1.dev.app.model.entity.Reclamo;

@Service
public class ReclamoMapper implements Function<Reclamo,ReclamoDTO> {

	@Override
	public ReclamoDTO apply(Reclamo reclamo) {
		// TODO Auto-generated method stub
		return new ReclamoDTO(
				reclamo.getId(),
				reclamo.getTitulo(),
				reclamo.getUser(),
				reclamo.getDescripcion(),
				reclamo.getEstadoReclamo(),
				reclamo.getTipoReclamo(),
				reclamo.getActualizacion(),
				reclamo.getEdificio()
				);
	}
	

}
