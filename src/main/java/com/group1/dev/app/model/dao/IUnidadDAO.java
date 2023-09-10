package com.group1.dev.app.model.dao;

import java.util.List;

import com.group1.dev.app.model.entity.Unidad;

public interface IUnidadDAO {
	
	public List<Unidad> findAll();

	public Unidad findById(int id);

	public void save(Unidad unidad);

	public void deleteById(int id);

}
