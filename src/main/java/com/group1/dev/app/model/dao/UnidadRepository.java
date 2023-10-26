package com.group1.dev.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EstadoUnidad;
import com.group1.dev.app.model.entity.Unidad;

public interface UnidadRepository extends JpaRepository<Unidad,Integer>{

	List<Unidad> findByEdificio(Edificio edificio);
	
	List<Unidad> findByEstado(EstadoUnidad estadoUnidad);
	
}
