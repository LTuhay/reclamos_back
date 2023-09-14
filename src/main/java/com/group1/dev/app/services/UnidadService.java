package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.UnidadRepository;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class UnidadService implements IUnidadService {
	
	@Autowired
	private UnidadRepository unidadRepo;

	@Override
	public List<Unidad> findAll() {
		return (List<Unidad>) unidadRepo.findAll();
	}

	@Override
	public Optional<Unidad> findById(int id) {
		return unidadRepo.findById(id);
	}

	@Override
	public void save(Unidad unidad) {
		unidadRepo.save(unidad);
	}
	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		unidadRepo.deleteById(id);
	}

}
