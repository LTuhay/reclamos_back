package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.UserRepository;
import com.group1.dev.app.model.entity.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
	
		return userRepository.findById(id);
	}

	@Override
	public void save(User user) {		
		
		userRepository.save(user);

	}

	@Override
	public void deleteById(int id) {
		
		userRepository.deleteById(id);
		
	}

	public Optional<User> findByUsername(String username){
		
		return null;
	
	}

}
