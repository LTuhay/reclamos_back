package com.group1.dev.app.services;

import java.util.ArrayList;
import java.util.List;

import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.TipoReclamo;

public interface IReclamoService {

	public ArrayList<Reclamo> findAll();

	public Reclamo findById(Integer id);

	public void save(Reclamo reclamo);

	public void deleteById(Integer id);

	public void update(Integer id,Reclamo reclamoNew);

	public List<Reclamo> filter2(Integer userId, Integer buildingId, EstadoReclamo state, TipoReclamo type);

}
