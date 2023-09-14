package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Imagen;

public interface IImagenService {

	public List<Imagen> findAll();

	public Optional<Imagen> findById(int id);

	public void save(Imagen imagen);

	public void deleteById(int id);
	
}
