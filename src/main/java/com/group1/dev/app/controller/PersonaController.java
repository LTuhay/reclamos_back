package com.group1.dev.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

		Optional<Persona> persona = personaService.findById(personaId);
		if (!persona.isPresent()) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Persona>(persona.get(), HttpStatus.OK);

	}
	
	/*

	@GetMapping(value = "/findDni")
	public ResponseEntity<?> getPersonaDni(@RequestParam("dni") int personaDni) {

		Persona persona = personaService.findPersonaDni(personaDni);
		if (persona == null) {
			String mensaje = "Persona no encontrada con DNI: " + personaDni;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(persona);

	}

	@GetMapping(value = "/findApellido")
	public ResponseEntity<?> getPersonaApellido(@RequestParam("apellido") String personaApellido) {

		List<Persona> personas = personaService.findPersonaApellido(personaApellido);
		if (personas.isEmpty()) {
			String mensaje = "Persona no encontrada con apellido: " + personaApellido;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(personas);
		}

	}

	@PutMapping(value = "/update/{dni}")
	public ResponseEntity<?> updatePersona(@PathVariable int dni, @RequestBody Persona updatedPersona) {
		Optional<Persona> existingPersonaOptional = Optional.of(personaService.findPersonaDni(dni));

		if (existingPersonaOptional.isPresent()) {
			Persona existingPersona = existingPersonaOptional.get();

			existingPersona.setNombre(updatedPersona.getNombre());
			existingPersona.setApellido(updatedPersona.getApellido());
			existingPersona.setDni(updatedPersona.getDni());
			existingPersona.setTipoPersona(updatedPersona.getTipoPersona());
			existingPersona.setUnidad(updatedPersona.getUnidad());

			personaService.save(existingPersona);

			return ResponseEntity.ok(existingPersona);
		} else {
			String mensaje = "Persona no encontrada con DNI: " + dni;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePersona(@RequestParam("id") int personaId) {

		Optional<Persona> persona = personaService.findById(personaId);
		if (!persona.isPresent()) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		personaService.deleteById(personaId);
		String mensaje = "Persona eliminada con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}*/

}
