package com.group1.dev.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.group1.dev.app.model.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona,Integer> {

}
