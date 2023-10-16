package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public Optional<User> findById(int id);

	public void save(User user);

	public void deleteById(int id);

}
