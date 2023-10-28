package com.group1.dev.app.controller;

import java.util.List;
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

import com.group1.dev.app.dto.EdificioDTO;
import com.group1.dev.app.mappers.EdificioMapper;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IEdificioService;

@RestController
@RequestMapping("/edificios")
public class EdificioController {

	@Autowired
	private IEdificioService edificioService;
	
	@Autowired
	private EdificioMapper edificioMapper;
	
	@GetMapping("/all")
	public List<EdificioDTO> findAll(){
		
		return edificioService
				.findAll()
				.stream()
				.map(edificioMapper)
				.collect(Collectors.toList());	
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<?> getEdificio(@RequestParam("address") String address) {
		
		Optional<EdificioDTO> edificio = edificioService.findByDireccion(address).map(edificioMapper);
		if (!edificio.isPresent()) {
			String mensaje = "Edificio no encontrado con el domicilio: " + address;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(edificio.get(), HttpStatus.OK);

	}
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addEdificio(@RequestParam("address") String address) {
		
		Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (edificio.isPresent()) {
			String mensaje = "Ya existe un Edificio registrado con ese domicilio";
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		}
		
		Edificio building = new Edificio();
		building.setDireccion(address);
		edificioService.save(building);
		return new ResponseEntity<String>("Edificio Creado con Exito", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEdificio(@RequestParam("address") String address) {

		Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (!edificio.isPresent()) {
			String mensaje = "Edificio no encontrado con el domicilio: " + address;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		edificioService.deleteById(edificio.get().getId());
		String mensaje = "Edificio eliminado con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
	
	
	@PostMapping("/addUnidad")
	public ResponseEntity<String> addPersona(@RequestParam("address") String address, @RequestBody Unidad unidad) {
        Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (!edificio.isPresent()) {
			String mensaje = "Edificio no encontrado con direccion: " + address;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
        edificioService.addUnidad(edificio.get(),unidad);
        edificioService.save(edificio.get());
        String mensaje = "Unidad agregada con exito";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);        
	}
	
	//----------------------ACA---------------------//
	

	@PostMapping("/delUnidad")
	public ResponseEntity<String> delUnidad(@RequestParam("ide") int edificioId, @RequestParam("idu") int unidadId) {
	    Optional<Edificio> edificioOptional = edificioService.findById(edificioId);
	    if (!edificioOptional.isPresent()) {
	        String mensaje = "Edificio no encontrado con ID: " + edificioId;
	        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    }
	    Edificio edificio = edificioOptional.get();
	    Optional<Unidad> unidadOptional = edificio.getUnidades().stream()
	            .filter(unidad -> unidad.getId() == unidadId)
	            .findFirst();
	    if (!unidadOptional.isPresent()) {
	        String mensaje = "Unidad no encontrada con ID: " + unidadId;
	        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    }
	    Unidad unidad = unidadOptional.get();
	    edificioService.delUnidad(edificio, unidad);
	    edificioService.save(edificio);
	    String mensaje = "Unidad eliminada con éxito del edificio";
	    return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	
	@GetMapping(value = "/findUnidades")	
    public ResponseEntity<?> findPersonasByUnidadId(@RequestParam("id") int edificioId) {
        Optional<Edificio> edificioOptional = edificioService.findById(edificioId);
		if (!edificioOptional.isPresent()) {
			String mensaje = "Edificio no encontrado con ID: " + edificioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
         Edificio edificio = edificioOptional.get();
         List<Unidad> unidades = edificioService.findUnidadesByEdificio(edificio);
         if (unidades.isEmpty()) {
        	 String mensaje = "El edificio no contiene unidades: " + edificioId;
        	 return new ResponseEntity<>(mensaje,HttpStatus.NOT_FOUND);
         }
 		return new ResponseEntity<>(unidades, HttpStatus.OK);
        }
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateEdificio(@PathVariable int id, @RequestBody Edificio updatedEdificio) {
        Optional<Edificio> edificioOptional = edificioService.findById(id);

		if (edificioOptional.isPresent()) {
			Edificio edificio = edificioOptional.get();
			
			edificio.setDireccion(updatedEdificio.getDireccion());
			edificio.setReclamos(updatedEdificio.getReclamos());
			edificio.setUnidades(updatedEdificio.getUnidades());

	        edificioService.save(edificio);

			return ResponseEntity.ok(edificio);
		} else {
			String mensaje = "Edificio no encontrado con id: " + id;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
	}
	
}
