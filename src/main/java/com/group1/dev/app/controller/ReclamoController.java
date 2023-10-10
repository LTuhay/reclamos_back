package com.group1.dev.app.controller;


import java.util.ArrayList;


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



import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.services.ReclamoService;
import com.group1.dev.app.exceptions.ReclamoNotFoundException;


@RestController
@RequestMapping("/reclamo")
public class ReclamoController {
	
	@Autowired
	private ReclamoService reclamoService;

	@GetMapping("/all")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ArrayList<Reclamo>> getAll() {
	    ArrayList<Reclamo> allReclamos = reclamoService.findAll();
	    if (allReclamos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.ok(allReclamos);
	    }
	}
	
	
	@GetMapping("/filter")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ArrayList<Reclamo>> filterReclamos(@RequestParam(name = "userid", required = false) Integer userId, @RequestParam(name = "buildingid", required = false) Integer buildingId, @RequestParam(name = "state", required = false) String state, @RequestParam(name = "type", required = false) String type ) {
	    ArrayList<Reclamo> reclamos = reclamoService.filter(userId,buildingId,state,type);
	   if (reclamos.isEmpty()) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	   else {
	    return ResponseEntity.ok(reclamos);}
	}
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<Reclamo> findById(@PathVariable Integer id) {
		Reclamo reclamo = reclamoService.findById(id);
		if (reclamo != null) {
			return ResponseEntity.ok(reclamo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/add")
    public ResponseEntity<String> addReclamo(@RequestBody Reclamo reclamo) {
        
		try {
		reclamoService.save(reclamo);
        
		 return ResponseEntity.ok().body("Reclamo creado exitosamente");}
		
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el reclamo.");
			
			
		}
    }
	
	@PatchMapping("/patch/{id}")
	public ResponseEntity<?> actualizarReclamo(@PathVariable Integer id, @RequestBody Reclamo reclamo) {

		
		try {
			reclamoService.update(id,reclamo);
			return ResponseEntity.ok("Reclamo actualizado exitosamente");
			
		}
		
		catch (Exception e){
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
