package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Reclamo;

public interface IReclamoService {
	
	public List<Reclamo> findAll();

	public Optional<Reclamo> findById(int id);

	public void save(Reclamo reclamo);

	public void deleteById(int id);

}
