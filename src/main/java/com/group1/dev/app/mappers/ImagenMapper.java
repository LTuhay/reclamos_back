package com.group1.dev.app.mappers;

import java.util.function.Function;

import com.group1.dev.app.dto.ImagenDTO;
import com.group1.dev.app.model.entity.Imagen;

public class ImagenMapper implements Function<Imagen,ImagenDTO>{

	@Override
	public ImagenDTO apply(Imagen imagen) {
		// TODO Auto-generated method stub
		return new ImagenDTO(
				imagen.getNombreImagen(),
				imagen.getDescripcion(),
				imagen.getDatosImagen().toString(),
				imagen.getReclamo().toString()
				);
	}

}
