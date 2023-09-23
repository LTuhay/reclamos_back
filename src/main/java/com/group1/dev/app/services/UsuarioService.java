package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.UsuarioRepository;
import com.group1.dev.app.model.dao.UsuarioRepositoryImpl;
import com.group1.dev.app.model.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private UsuarioRepositoryImpl usuarioRepoImpl;

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Optional<Usuario> findById(int id) {
		return usuarioRepo.findById(id);
	}

	@Override
	public void save(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(hashedPassword);
		usuarioRepo.save(usuario);
	}

	@Override
	public void deleteById(int id) {
		usuarioRepo.deleteById(id);

	}

	@Override
	public Usuario findUser(String username, String password) {
		
		return usuarioRepoImpl.findUser(username, password);
	}

}

