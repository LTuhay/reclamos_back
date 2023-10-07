package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.PersonaRepository;
import com.group1.dev.app.model.dao.PersonaRepositoryImpl;
import com.group1.dev.app.model.entity.Persona;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepo;
	
	@Autowired
	private PersonaRepositoryImpl personaRepoImpl;

	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaRepo.findAll();
	}

	@Override
	public Optional<Persona> findById(int id) {
		return personaRepo.findById(id);
	}

	@Override
	public void save(Persona persona) {
		personaRepo.save(persona);
	}

	@Override
	public void deleteById(int id) {
		personaRepo.deleteById(id);
	}

	@Override
	public Persona findPersonadni(int dni) {
		return personaRepoImpl.findPersonadni(dni);
	}

}
