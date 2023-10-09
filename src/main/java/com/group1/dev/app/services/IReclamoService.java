package com.group1.dev.app.services;

import java.util.ArrayList;


import com.group1.dev.app.model.entity.ReclamoDTO;

public interface IReclamoService  {
	
	public ArrayList<ReclamoDTO> findAll();

	public ReclamoDTO findById(int id);

	public void save(ReclamoDTO reclamoDTO);

	public void deleteById(int id);
	
	public void update(ReclamoDTO reclamoDTO) ;
	
	public ArrayList<ReclamoDTO> filter(int userId, int buildingId, String state, String type);

}
