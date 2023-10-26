package com.group1.dev.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.group1.dev.app.auth.AuthService;
import com.group1.dev.app.auth.RegisterRequest;
import com.group1.dev.app.model.entity.TipoPersona;

public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private AuthService authService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		authService.register(new RegisterRequest("admin","admin",0,0,"admin","admin",TipoPersona.Administrador));	
	}

}
