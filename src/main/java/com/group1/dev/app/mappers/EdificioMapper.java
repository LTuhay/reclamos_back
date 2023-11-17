package com.group1.dev.app.mappers;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.EdificioDTO;
import com.group1.dev.app.model.entity.Edificio;

@Service
public class EdificioMapper implements Function<Edificio,EdificioDTO> {
	
	@Autowired
	private UnidadMapper unidadMapper;

	@Override
	public EdificioDTO apply(Edificio edificio) {
		// TODO Auto-generated method stub
		return new EdificioDTO(
				edificio.getId(),
				edificio.getDireccion(),
				edificio.getUnidades()
				.stream()
				.map(unidadMapper)
				.collect(Collectors.toList())
				);
	}

}
