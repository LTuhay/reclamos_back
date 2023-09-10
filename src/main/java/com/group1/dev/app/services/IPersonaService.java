package com.group1.dev.app.services;

import java.util.List;

import com.group1.dev.app.model.entity.Persona;

public interface IPersonaService {

	public List<Persona> findAll();

	public Persona findById(int id);

	public void save(Persona persona);

	public void update(int personaId, Persona persona);

	public void deleteById(int id);

}
