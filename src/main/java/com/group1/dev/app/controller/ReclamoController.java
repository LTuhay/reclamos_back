package com.group1.dev.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.TipoReclamo;
import com.group1.dev.app.services.EdificioService;
import com.group1.dev.app.services.ReclamoService;
import com.group1.dev.app.services.UserService;
import com.group1.dev.app.dto.ReclamoDTO;
import com.group1.dev.app.exceptions.ReclamoNotFoundException;
import com.group1.dev.app.mappers.ReclamoMapper;

@RestController
@RequestMapping("/reclamo")
public class ReclamoController {

	@Autowired
	private ReclamoService reclamoService;

	@Autowired
	private ReclamoMapper reclamoMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EdificioService edificioService;

	@GetMapping("/all")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ReclamoDTO>> getAll() {
		List<ReclamoDTO> allReclamos = reclamoService.findAll().stream().map(reclamoMapper)
				.collect(Collectors.toList());

		if (allReclamos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.ok(allReclamos);
		}
	}

	@GetMapping("/filter")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filterReclamos(@RequestParam(name = "userid", required = false) Integer userId,
			@RequestParam(name = "buildingid", required = false) Integer buildingId,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "type", required = false) String type) {
		List<Reclamo> reclamos = reclamoService.filter2(userId, buildingId, state, type);
		List<ReclamoDTO> allreclamos = reclamos.stream().map(reclamoMapper)
				.collect(Collectors.toList());

		if (reclamos == null || reclamos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			///List<ReclamoDTO> reclamosDTO = reclamos.stream().map(reclamoMapper).collect(Collectors.toList());
			return ResponseEntity.ok(allreclamos);
		}
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<ReclamoDTO> findById(@PathVariable Integer id) {

		Reclamo reclamo = reclamoService.findById(id);

		if (reclamo != null) {

			ReclamoDTO reclamoDTO = reclamoMapper.apply(reclamo);

			return ResponseEntity.ok(reclamoDTO);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/add")
	public ResponseEntity<String> addReclamo(@RequestBody ReclamoDTO reclamoDTO) {

		Map<String, Object> reclamoMap = reclamoDTO.toMap();

		System.out.println(reclamoMap);

		Reclamo reclamo = new Reclamo();
		
		Optional<EntityUser> optionalUser = userService.findById((int) reclamoMap.get("userid"));
		EntityUser user = optionalUser.get();
		
		Optional<Edificio> optionalEdificio = edificioService.findById((int) reclamoMap.get("edificioid"));
		
		Edificio edificio = optionalEdificio.get();
		
		reclamo.setId((Integer) reclamoMap.get("reclamo_id"));
		reclamo.setUser(user);		
		reclamo.setTitulo((String) reclamoMap.get("titulo"));
		reclamo.setDescripcion((String) reclamoMap.get("descripcion"));
		reclamo.setEstadoReclamo(EstadoReclamo.valueOf((String) reclamoMap.get("estadoReclamo")));
		reclamo.setTipoReclamo(TipoReclamo.valueOf(reclamoMap.get("tipoReclamo").toString()));
		reclamo.setEdificio(edificio);
		reclamo.setActualizacion((String) reclamoMap.get("actualizacion"));

		try {
			reclamoService.save(reclamo);

			return ResponseEntity.ok().body("Reclamo creado exitosamente");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el reclamo.");
		}
	}

	@PatchMapping("/patch/{id}")
	public ResponseEntity<?> actualizarReclamo(@PathVariable Integer id, @RequestBody ReclamoDTO reclamoDTO) {

		Map<String, Object> reclamoMap = reclamoDTO.toMap();
		
		Optional<EntityUser> optionalUser = userService.findById((int) reclamoMap.get("userid"));
		EntityUser user = optionalUser.get();
		
		Optional<Edificio> optionalEdificio = edificioService.findById((int) reclamoMap.get("edificioid"));
		
		Edificio edificio = optionalEdificio.get();

		Reclamo reclamo = new Reclamo();
		reclamo.setId(id);
		reclamo.setUser(user);
		reclamo.setTitulo((String) reclamoMap.get("titulo"));
		reclamo.setDescripcion((String) reclamoMap.get("descripcion"));
		reclamo.setEstadoReclamo(EstadoReclamo.valueOf((String) reclamoMap.get("estadoReclamo")));
		reclamo.setTipoReclamo(TipoReclamo.valueOf(reclamoMap.get("tipoReclamo").toString()));
		reclamo.setEdificio(edificio);
		reclamo.setActualizacion((String) reclamoMap.get("actualizacion"));
		try {
			reclamoService.update(id, reclamo);
			return ResponseEntity.ok("Reclamo actualizado exitosamente");

		}

		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el reclamo.");
		}

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
		try {
			reclamoService.deleteById(id);
			return ResponseEntity.ok("reclamo eliminado exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el estado.");
		}
	}

	@ExceptionHandler(ReclamoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> reclamoNotFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El reclamo no se encontró");
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> exceptionHandler() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	}

}
