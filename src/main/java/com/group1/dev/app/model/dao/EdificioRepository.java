package com.group1.dev.app.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Persona;


@Repository
public interface EdificioRepository extends CrudRepository<Edificio,Integer> {


}