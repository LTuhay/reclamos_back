package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.EdificioDTO;
import com.group1.dev.app.model.dao.EdificioRepository;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Unidad;

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
		return edificioRepo.findById(id);
	}

	@Override
	public Optional<Edificio> findByDireccion(String direccion) {
		return edificioRepo.findByDireccion(direccion);
	}

	@Override
	public void save(Edificio edificio) {
		edificioRepo.save(edificio);
	}

	@Override
	public void deleteById(int id) {
		edificioRepo.deleteById(id);

	}
	
	public void deleteByDireccion(String direccion) {
		edificioRepo.deleteByDireccion(direccion);
	}

	@Override
	public void addUnidad(Edificio edificio, Unidad unidad) {
		unidad.setEdificio(edificio);
		edificio.getUnidades().add(unidad);
		edificioRepo.save(edificio);
	}

	@Override
	public void delUnidad(Edificio edificio, Unidad unidad) {
		edificio.getUnidades().remove(unidad);
		unidad.setEdificio(null);
		edificioRepo.save(edificio);
	}

	@Override
	public List<Unidad> findUnidadesByEdificio(Edificio edificio) {
		return edificio.getUnidades();
	}

}
