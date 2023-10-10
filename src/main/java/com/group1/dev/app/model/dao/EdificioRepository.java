package com.group1.dev.app.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.group1.dev.app.model.entity.Edificio;

public interface EdificioRepository extends CrudRepository<Edificio,Integer> {

	Optional<Edificio> findByDireccion(String direccion);

}