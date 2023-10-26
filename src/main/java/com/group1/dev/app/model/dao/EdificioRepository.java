package com.group1.dev.app.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group1.dev.app.model.entity.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio,Integer> {

	//Query Method
	Optional<Edificio> findByDireccion(String direccion);
	
	void deleteByDireccion(String direccion);

}