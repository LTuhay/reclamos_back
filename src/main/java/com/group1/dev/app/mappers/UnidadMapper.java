package com.group1.dev.app.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.UnidadDTO;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class UnidadMapper implements Function<Unidad,UnidadDTO> {

	@Override
	public UnidadDTO apply(Unidad unidad) {
		// TODO Auto-generated method stub
		return new UnidadDTO(
				unidad.getNro(),
				unidad.getPiso(),
				unidad.getEstado().name()
				);
	}

}
