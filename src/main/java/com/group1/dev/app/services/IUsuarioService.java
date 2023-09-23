package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group1.dev.app.model.entity.Usuario;
@Service
public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public Optional<Usuario> findById(int id);

	public Usuario findUser(String username, String password);

	public void save(Usuario usuario);

	public void deleteById(int id);

}
