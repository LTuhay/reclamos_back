package com.group1.dev.app.services;

import java.util.ArrayList;

import com.group1.dev.app.model.entity.Reclamo;

public interface IReclamoService {

	public ArrayList<Reclamo> findAll();

	public Reclamo findById(Integer id);

	public void save(Reclamo reclamo);

	public void deleteById(Integer id);

	public void update(Integer id,Reclamo reclamoNew);

	public ArrayList<Reclamo> filter(Integer userId, Integer buildingId, String state, String type);

}
