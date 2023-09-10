package com.group1.dev.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.IPersonaDAO;
import com.group1.dev.app.model.entity.Persona;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonaDAO personasDAO;

	@Override
	public List<Persona> findAll() {

		List<Persona> personas = personasDAO.findAll();
		return personas;
	}

	@Override
	public Persona findById(int id) {

		Persona persona = personasDAO.findById(id);
		return persona;
	}

	@Override
	public void save(Persona persona) {
		// TODO Auto-generated method stub
		personasDAO.save(persona);
	}

	@Override
	public void update(int personaId, Persona persona) {
		// TODO Auto-generated method stub
		Persona personaExistente = personasDAO.findById(personaId);

		if (personaExistente != null) {

			personaExistente.setNombre(persona.getNombre());
			personaExistente.setApellido(persona.getApellido());
			personaExistente.setDni(persona.getDni());
			personaExistente.setTipoPersona(persona.getTipoPersona());
			personaExistente.setUnidad(persona.getUnidad());
		}

		personasDAO.save(personaExistente);
	}

	@Override
	public void deleteById(int id) {

		personasDAO.deleteById(id);
	}

}
