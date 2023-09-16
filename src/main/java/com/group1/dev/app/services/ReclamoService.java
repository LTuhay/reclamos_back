package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.entity.Reclamo;
@Service
public class ReclamoService implements IReclamoService {
	
	@Autowired
	private ReclamoRepository reclamoRepo;

	@Override
	public List<Reclamo> findAll() {
		return (List<Reclamo>)reclamoRepo.findAll();
	}

	@Override
	public Optional<Reclamo> findById(int id) {
		return reclamoRepo.findById(id);
	}

	@Override
	public void save(Reclamo reclamo) {
		reclamoRepo.save(reclamo);
		
	}

	@Override
	public void deleteById(int id) {
		reclamoRepo.deleteById(id);
		
	}

}
