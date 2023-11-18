package com.group1.dev.app.mappers;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.UnidadDTO;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class UnidadMapper implements Function<Unidad,UnidadDTO> {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UnidadDTO apply(Unidad unidad) {
		// TODO Auto-generated method stub
		return new UnidadDTO(
				unidad.getId(),
				unidad.getNro(),
				unidad.getPiso(),
				unidad.getEstado().name(),
				Integer.valueOf(unidad.getEdificio().getId()),
				unidad.getPersonas()
				.stream()
				.map(userMapper)
				.collect(Collectors.toList())
				);			
	}

}