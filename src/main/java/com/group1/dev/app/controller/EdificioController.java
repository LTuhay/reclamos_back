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

import com.group1.dev.app.dto.EdificioDTO;
import com.group1.dev.app.dto.UnidadDTO;
import com.group1.dev.app.mappers.EdificioMapper;
import com.group1.dev.app.mappers.UnidadMapper;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IEdificioService;

@RestController
@RequestMapping("/edificios")
public class EdificioController {

	@Autowired
	private IEdificioService edificioService;
	
	@Autowired
	private UnidadMapper unidadMapper;
	
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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(edificio.get(), HttpStatus.OK);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addEdificio(@RequestBody Map<String, String> requestBody) {
	    String address = requestBody.get("direccion");
		Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (edificio.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		Edificio building = new Edificio();
		building.setDireccion(address);		
		Edificio edificioGuardado = edificioService.save(building);
		return new ResponseEntity<String>(String.valueOf(edificioGuardado.getId()), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEdificio(@RequestParam("address") String address) {

		Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (!edificio.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		edificioService.deleteById(edificio.get().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/addUnidad")
	public ResponseEntity<String> addUnidad(@RequestParam("address") String address, @RequestBody Unidad unidad) {
        Optional<Edificio> edificio = edificioService.findByDireccion(address);
		if (!edificio.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
        edificioService.addUnidad(edificio.get(),unidad);
        edificioService.save(edificio.get());
        return new ResponseEntity<>(HttpStatus.CREATED);        
	}
	
	//----------------------ACA---------------------//
	

	@PostMapping("/delUnidad")
	public ResponseEntity<String> delUnidad(@RequestParam("ide") int edificioId, @RequestParam("idu") int unidadId) {
	    Optional<Edificio> edificioOptional = edificioService.findById(edificioId);
	    if (!edificioOptional.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    Edificio edificio = edificioOptional.get();
	    Optional<Unidad> unidadOptional = edificio.getUnidades().stream()
	            .filter(unidad -> unidad.getId() == unidadId)
	            .findFirst();
	    if (!unidadOptional.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    Unidad unidad = unidadOptional.get();
	    edificioService.delUnidad(edificio, unidad);
	    edificioService.save(edificio);
	    return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@GetMapping(value = "/findUnidades")	
    public ResponseEntity<?> findUnidadesbyEdificioId(@RequestParam("id") int id) {
        Optional<Edificio> edificioOptional = edificioService.findById(id);
		if (!edificioOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
         Edificio edificio = edificioOptional.get();
         List<Unidad> unidades = edificioService.findUnidadesByEdificio(edificio);
	    List<UnidadDTO> unidadDTOs = unidades.stream()
	            .map(unidadMapper::apply)
	            .collect(Collectors.toList());
 		return new ResponseEntity<>(unidadDTOs, HttpStatus.OK);
        }
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateEdificio(@PathVariable int id, @RequestBody Map<String, String> requestBody) {  

		Optional<Edificio> edificioOptional = edificioService.findById(id);
	    String address = requestBody.get("direccion");
		System.out.println("direccion"+ address);
		if (edificioOptional.isPresent()) {
			Edificio edificio = edificioOptional.get();			
			edificio.setDireccion(address);
	        edificioService.save(edificio);
	 		return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
}
