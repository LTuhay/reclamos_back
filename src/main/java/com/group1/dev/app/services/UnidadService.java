package com.group1.dev.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.IUnidadDAO;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class UnidadService implements IUnidadService {
	
	@Autowired
	private IUnidadDAO unidadDAO;

	@Override
	public List<Unidad> findAll() {
		// TODO Auto-generated method stub
		List<Unidad> unidades = unidadDAO.findAll();
		return unidades;
	}

	@Override
	public Unidad findById(int id) {
		// TODO Auto-generated method stub
		Unidad unidad = unidadDAO.findById(id);
		return unidad;
	}

	@Override
	public void save(Unidad unidad) {
		// TODO Auto-generated method stub
		unidadDAO.save(unidad);
	}

	@Override
	public void update(int unidadId, Unidad unidad) {
		// TODO Auto-generated method stub
		Unidad unidadExistente = unidadDAO.findById(unidadId);
		if (unidadExistente != null) {
			
			unidadExistente.setNro(unidad.getNro());
			unidadExistente.setEdificio(unidad.getEdificio());
			unidadExistente.setEstado(unidad.getEstado());
			unidadExistente.setPiso(unidad.getPiso());
			unidadExistente.setPersonas(unidad.getPersonas());
		}
		
		unidadDAO.save(unidadExistente);
	}
	
	@Override
	public void setPersona(int unidadId, Persona persona) {
		// TODO Auto-generated method stub
		Unidad unidadExistente = unidadDAO.findById(unidadId);
		if (unidadExistente != null) {
			
			unidadExistente.getPersonas().add(persona);
		}
		
		unidadDAO.save(unidadExistente);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		unidadDAO.deleteById(id);
	}

}
