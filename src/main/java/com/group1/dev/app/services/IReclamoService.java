package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Reclamo;

public interface IReclamoService {
<<<<<<< Updated upstream
	
	public List<Reclamo> findAll();

	public Optional<Reclamo> findById(int id);
=======

	public ArrayList<Reclamo> findAll();

	public Reclamo findById(int id);
>>>>>>> Stashed changes

	public void save(Reclamo reclamo);

	public void deleteById(int id);
<<<<<<< Updated upstream
=======

	public void update(Reclamo reclamoNew, Reclamo reclamoOld);

	public ArrayList<Reclamo> filter(Integer userId, Integer buildingId, String state, String type);
>>>>>>> Stashed changes

}
