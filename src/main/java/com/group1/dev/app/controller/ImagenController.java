package com.group1.dev.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group1.dev.app.model.entity.Imagen;
import com.group1.dev.app.services.ImagenService;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
	@Autowired
	private ImagenService imagenService;

	@PostMapping("/subir")
	public ResponseEntity<String> upload(@RequestParam("archivo") MultipartFile archivo) {
		try {
			Imagen imagen = new Imagen();
			imagen.setDatosImagen(archivo.getBytes());
			imagenService.save(imagen);
			return ResponseEntity.ok("Imagen subida exitosamente.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> download(@PathVariable int id) {
		Optional<Imagen> imagen = imagenService.findById(id);
		if (imagen.isPresent()) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen.get().getDatosImagen());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
