package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.IPersonaRepository;
import com.group1.dev.app.model.dao.IPersonaRepositoryCustom;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.PersonaDTO;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonaRepository personaRepo;
	
	@Autowired
	private IPersonaRepositoryCustom personaRepoImpl;
	
	@Autowired
	private ModelMapper modelMapper;

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
	public Persona findPersonaDni(int dni) {
		return personaRepoImpl.findPersonaDni(dni);
	}

	@Override
	public List<Persona> findPersonaApellido(String apellido) {
		return personaRepoImpl.findPersonaApellido(apellido);
	}

	@Override
	public PersonaDTO personaToDTO(Persona persona) {
		return modelMapper.map(persona, PersonaDTO.class);
	}

	@Override
	public Persona dtoToPersona(PersonaDTO personaDTO) {
		return modelMapper.map(personaDTO, Persona.class);
	}

}
