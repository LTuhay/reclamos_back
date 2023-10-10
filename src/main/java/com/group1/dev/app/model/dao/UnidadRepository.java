package com.group1.dev.app.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;

public interface UnidadRepository extends CrudRepository<Unidad,Integer>{

	List<Unidad> findByEdificio(Edificio edificio);
	
}
