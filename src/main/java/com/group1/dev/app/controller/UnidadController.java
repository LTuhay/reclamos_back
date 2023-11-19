package com.group1.dev.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.group1.dev.app.dto.UnidadDTO;
import com.group1.dev.app.dto.UnidadUpdateDTO;
import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.mappers.UnidadMapper;
import com.group1.dev.app.mappers.UserMapper;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.EstadoUnidad;
import com.group1.dev.app.model.entity.TipoReclamo;
import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IEdificioService;
import com.group1.dev.app.services.IUnidadService;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

	@Autowired
	private IUnidadService unidadService;
	@Autowired
	private IEdificioService edificioService;
	@Autowired
	private UnidadMapper unidadMapper;
	@Autowired
	private UserMapper userMapper;
	

	@GetMapping("/all")
	public List<UnidadDTO> findAll () {
		List<Unidad> unidades = unidadService.findAll();
	    List<UnidadDTO> unidadDTOs = unidades.stream()
	            .map(unidadMapper::apply)
	            .collect(Collectors.toList());

	    return unidadDTOs;
	}

	@GetMapping(value = "/findById")
	public ResponseEntity<?> getUnidad(@RequestParam("id") int unidadId) {
		Optional<Unidad> optionalUnidad = unidadService.findById(unidadId);
		if (!optionalUnidad.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Unidad unidad = optionalUnidad.get();
		UnidadDTO unidadDTO = unidadMapper.apply(unidad);
		return new ResponseEntity<>(unidadDTO, HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<?> addUnidad(@RequestBody UnidadDTO unidadDTO) {		
	    try {
	        Map<String, Object> unidadMap = unidadDTO.toMap();
	        System.out.println(unidadMap);
	        Unidad unidad = new Unidad();
	        Optional<Edificio> optionalEdificio = edificioService.findById((int) unidadMap.get("edificioID"));
	        Edificio edificio = optionalEdificio.get();
	        unidad.setEdificio(edificio);
	        unidad.setEstado(EstadoUnidad.valueOf(unidadMap.get("estado").toString()));
	        unidad.setNro((int) unidadMap.get("nro"));
	        unidad.setPiso((int) unidadMap.get("piso"));	               
	        unidadService.save(unidad);	               
	        return new ResponseEntity<UnidadDTO>(unidadDTO, HttpStatus.CREATED);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la unidad.");
	    }
	}



	
	/* aca */
	@PostMapping("/addPersona")
	public ResponseEntity<String> addPersona(@RequestParam("idu") int unidadId, @RequestParam("idp") int personaId) {
		Optional<Unidad> unidadOptional = unidadService.findById(unidadId);
		if (!unidadOptional.isPresent()) {
			String mensaje = "Unidad no encontrada con ID: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Unidad unidad = unidadOptional.get();
		Optional<EntityUser> personaOptional = unidad.getPersonas().stream()
				.filter(persona -> persona.getId() == personaId).findFirst();
		if (!personaOptional.isPresent()) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		EntityUser persona = personaOptional.get();
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
		Optional<EntityUser> personaOptional = unidad.getPersonas().stream()
				.filter(persona -> persona.getId() == personaId).findFirst();
		if (!personaOptional.isPresent()) {
			String mensaje = "Persona no encontrada con ID: " + personaId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		EntityUser persona = personaOptional.get();
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
		List<EntityUser> personas = unidadService.findPersonasByUnidadId(unidad);
		if (personas.isEmpty()) {
			String mensaje = "La unidad no contiene personas: " + unidadId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
	    List<UserDTO> userDTOs = personas.stream()
	            .map(userMapper::apply)
	            .collect(Collectors.toList());
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
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
	public ResponseEntity<?> updateUnidad(@PathVariable int id, @RequestBody UnidadUpdateDTO unidadUpdateDTO) {
	    try {
		Optional<Unidad> unidadOptional = unidadService.findById(id);
		if (unidadOptional.isPresent()) {			
			Unidad unidad = unidadOptional.get();
	        Map<String, Object> unidadMap = unidadUpdateDTO.toMap();
	        unidad.setEstado(EstadoUnidad.valueOf(unidadMap.get("estado").toString()));
	        unidad.setNro((int) unidadMap.get("nro"));
	        unidad.setPiso((int) unidadMap.get("piso"));	        
			unidadService.save(unidad);
			return ResponseEntity.ok(unidad);
		} else {
			String mensaje = "Unidad no encontrada con id: " + id;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la unidad.");
	    }
	}

}
