package com.group1.dev.app.mappers;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.group1.dev.app.dto.ReclamoDTO;
import com.group1.dev.app.model.entity.Reclamo;

@Service
public class ReclamoMapper implements Function<Reclamo, ReclamoDTO> {
    @Autowired
    ImagenMapper imagenMapper;

    @Override
    public ReclamoDTO apply(Reclamo reclamo) {
        return new ReclamoDTO(
        		reclamo.getId(),
                reclamo.getTitulo(),
                reclamo.getUser().getId(),
                reclamo.getDescripcion(),
                reclamo.getEstadoReclamo(),
                reclamo.getTipoReclamo(),
                reclamo.getActualizacion(),
                reclamo.getEdificio().getId()
        );
    }


}
