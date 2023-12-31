package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.Unidad;


public interface IUnidadService {

	public List<Unidad> findAll();

	public Optional<Unidad> findById(int id);

	public void addPersona(Unidad unidad, EntityUser persona);

	public void delPersona(Unidad unidad, EntityUser persona);

	public List<EntityUser> findPersonasByUnidadId(Unidad unidad);

	public void save(Unidad unidad);

	public void deleteById(int id);

}
