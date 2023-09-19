package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ImagenRepository;
import com.group1.dev.app.model.entity.Imagen;


@Service
public class ImagenService implements IImagenService {

	@Autowired
	private ImagenRepository imagenRepo;

	@Override
	public List<Imagen> findAll() {
		return (List<Imagen>) imagenRepo.findAll();
	}

	@Override
	public Optional<Imagen> findById(int id) {
		return imagenRepo.findById(id);
	}

	@Override
	public void save(Imagen imagen) {
		imagenRepo.save(imagen);
	}

	@Override
	public void deleteById(int id) {
		imagenRepo.deleteById(id);
	}

}
