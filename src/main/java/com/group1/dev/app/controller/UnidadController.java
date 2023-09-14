package com.group1.dev.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IUnidadService;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

	@Autowired
	private IUnidadService unidadService;
	
	@GetMapping("/all")
	public List<Unidad> findAll(){
		
		return unidadService.findAll();	
	}
	
	@GetMapping(value = "/find")
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
	
}
