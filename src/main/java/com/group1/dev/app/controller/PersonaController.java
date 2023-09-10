package com.group1.dev.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.services.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;

	@GetMapping(value = "/all")
	public List<Persona> findAll() {
		return personaService.findAll();
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> getPersona(@RequestParam("id") int personaId) {

		Persona persona = personaService.findById(personaId);
		if (persona == null) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(persona, HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {

		personaService.save(persona);
		return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePersona(@RequestParam("id") int personaId) {

		Persona persona = personaService.findById(personaId);
		if (persona == null) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		personaService.deleteById(personaId);
		String mensaje = "Persona eliminada con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);

	}
	
	

}
