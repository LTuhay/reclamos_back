package com.group1.dev.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group1.dev.app.model.entity.Imagen;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.services.ImagenService;
import com.group1.dev.app.services.ReclamoService;

@RestController
@RequestMapping("/imagen")
public class ImagenController {
	@Autowired
	private ImagenService imagenService;
	@Autowired
	private ReclamoService reclamoService;

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile archivo,
			@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
			@RequestParam("id_reclamo") Integer id_reclamo) {
		try {
			Imagen imagen = new Imagen();
			imagen.setNombreImagen(nombre);
			imagen.setDescripcion(descripcion);
			Reclamo reclamo = reclamoService.findById(id_reclamo);
			imagen.setReclamo(reclamo);
			imagen.setDatosImagen(archivo.getBytes());
			imagenService.save(imagen);
			return ResponseEntity.ok("Imagen subida exitosamente.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		try {
			imagenService.deleteById(id);
			return ResponseEntity.ok("Imagen eliminada exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la imagen.");
		}
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<byte[]> findById(@PathVariable int id) {
		Optional<Imagen> imagen = imagenService.findById(id);
		if (imagen.isPresent()) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen.get().getDatosImagen());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/findescriptiondbyid/{id}")
    public String findDescriptionById(@PathVariable int id) {
        Optional<Imagen> imagen = imagenService.findById(id);
        if (imagen.isPresent()) {
            System.out.println(imagen.get().getDescripcion());
            return imagen.get().getDescripcion();
        } else {
            return "Imagen no encontrada";
        }
    }

	@GetMapping("/findimagesreclamo/{id}")
	public ResponseEntity<?> findImagesbyReclamoId(@PathVariable int id) {
		Optional<Reclamo> reclamo = Optional.ofNullable(reclamoService.findById(id));
		if (reclamo.isPresent()) {
			return ResponseEntity.ok(reclamo.get().getFotos());
		} else {
			String mensaje = "Reclamo inexistente";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
	}
}
