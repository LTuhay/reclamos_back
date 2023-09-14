package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public Optional<Usuario> findById(int id);

	public void save(Usuario usuario);

	public void deleteById(int id);

}
