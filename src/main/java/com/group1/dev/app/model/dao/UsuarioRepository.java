package com.group1.dev.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.group1.dev.app.model.entity.Usuario;

public interface UsuarioRepository extends UsuarioRepositoryCustom, CrudRepository<Usuario,Integer> {

}