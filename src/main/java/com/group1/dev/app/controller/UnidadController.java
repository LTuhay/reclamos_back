package com.group1.dev.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IUnidadService;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

	@Autowired
	private IUnidadService unidadService;

	@GetMapping("/all")
	public List<Unidad> findAll() {

		return unidadService.findAll();
	}

	@GetMapping(value = "/findById")
	public ResponseEntity<?> getUnidad(@RequestParam("id") int unidadId) {
		Optional<Unidad> unidad = unidadService.findById(unidadId);
		if (!unidad.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(unidad.get(), HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Unidad> addUnidad(@RequestBody Unidad unidad) {

		unidadService.save(unidad);
		return new ResponseEntity<Unidad>(unidad, HttpStatus.CREATED);
	}

	@PostMapping("/addPersona")
	public ResponseEntity<String> addPersona(@RequestParam("id") int unidadId, @RequestBody Persona persona) {
		Optional<Unidad> unidadOptional = unidadService.findById(unidadId);
		if (!unidadOptional.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Unidad unidad = unidadOptional.get();
		unidadService.addPersona(unidad, persona);
		unidadService.save(unidad);
		String mensaje = "Persona agregada con exito a la unidad";
		return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

	}

	@PostMapping("/delPersona")
	public ResponseEntity<String> delPersona(@RequestParam("idu") int unidadId, @RequestParam("idp") int personaId) {
		Optional<Unidad> unidadOptional = unidadService.findById(unidadId);
		if (!unidadOptional.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Unidad unidad = unidadOptional.get();
		Optional<Persona> personaOptional = unidad.getPersonas().stream()
				.filter(persona -> persona.getId() == personaId).findFirst();
		if (!personaOptional.isPresent()) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		Persona persona = personaOptional.get();
		unidadService.delPersona(unidad, persona);
		unidadService.save(unidad);
		String mensaje = "Persona eliminada con éxito de la unidad";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	@GetMapping(value = "/findPersonas")
	public ResponseEntity<?> findPersonasByUnidadId(@RequestParam("id") int unidadId) {
		Optional<Unidad> unidadOptional = unidadService.findById(unidadId);
		if (!unidadOptional.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Unidad unidad = unidadOptional.get();
		List<Persona> personas = unidadService.findPersonasByUnidadId(unidad);
		if (personas.isEmpty()) {
			String mensaje = "La unidad no contiene personas: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUnidad(@RequestParam("id") int unidadId) {

		Optional<Unidad> unidad = unidadService.findById(unidadId);
		if (!unidad.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		unidadService.deleteById(unidadId);
		String mensaje = "Unidad eliminada con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUnidad(@PathVariable int id, @RequestBody Unidad updatedUnidad) {
		Optional<Unidad> unidadOptional = unidadService.findById(id);

		if (unidadOptional.isPresent()) {
			Unidad unidad = unidadOptional.get();

			unidad.setEdificio(updatedUnidad.getEdificio());
			unidad.setEstado(updatedUnidad.getEstado());
			unidad.setNro(updatedUnidad.getNro());
			unidad.setPersonas(updatedUnidad.getPersonas());
			unidad.setPiso(updatedUnidad.getPiso());
			unidadService.save(unidad);

			return ResponseEntity.ok(unidad);
		} else {
			String mensaje = "Unidad no encontrada con id: " + id;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

}
