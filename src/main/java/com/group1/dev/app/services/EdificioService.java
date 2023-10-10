package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.EdificioRepository;
import com.group1.dev.app.model.entity.Edificio;

@Service
public class EdificioService implements IEdificioService {

	@Autowired
	private EdificioRepository edificioRepo;

	 @Override
	   public List<Edificio> findAll() {
	      return (List<Edificio>) edificioRepo.findAll();

	   }

	   @Override
	   public Optional<Edificio> findById(int id) {
	      Optional<Edificio> optionalEdificio = edificioRepo.findById(id);

	   
	    if (optionalEdificio.isPresent()) {
	       return optionalEdificio;
	    } else {
	       return Optional.empty();
	    }
	   }

	@Override
	public void save(Edificio edificio) {
		edificioRepo.save(edificio);

	}

	@Override
	public void deleteById(int id) {
		edificioRepo.deleteById(id);

	}

}
