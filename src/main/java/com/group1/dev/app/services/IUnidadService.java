package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;


public interface IUnidadService {

	public List<Unidad> findAll();

	public Optional<Unidad> findById(int id);

	public void addPersona(Unidad unidad, Persona persona);

	public void delPersona(Unidad unidad, Persona persona);

	public List<Persona> findPersonasByUnidadId(Unidad unidad);

	public void save(Unidad unidad);

	public void deleteById(int id);

}
