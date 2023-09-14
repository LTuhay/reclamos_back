package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Edificio;

public interface IEdificioService {
	
	public List<Edificio> findAll();

	public Optional<Edificio> findById(int id);

	public void save(Edificio edificio);

	public void deleteById(int id);

}
