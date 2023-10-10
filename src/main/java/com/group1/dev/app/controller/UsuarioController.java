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

import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.UsuarioDTO;
import com.group1.dev.app.services.IUsuarioService;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    
    @GetMapping(value = "/all")
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> getUsuario(@RequestParam("id") int usuarioId) {

		Optional<Usuario> usuario = usuarioService.findById(usuarioId);
		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrada con ID: " + usuarioId;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);

	}
    
	@PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable int id, @RequestBody Usuario updatedUsuario) {
		Optional<Usuario> existingUsuarioOptional = usuarioService.findById(id);

		if (existingUsuarioOptional.isPresent()) {
			Usuario existingUsuario = existingUsuarioOptional.get();

			existingUsuario.setNombre(updatedUsuario.getNombre());
			existingUsuario.setApellido(updatedUsuario.getApellido());
			existingUsuario.setDni(updatedUsuario.getDni());
			existingUsuario.setTipoPersona(updatedUsuario.getTipoPersona());
			existingUsuario.setUnidad(updatedUsuario.getUnidad());

			usuarioService.save(existingUsuario);

			return ResponseEntity.ok(existingUsuario);
		} else {
			String mensaje = "Usuario no encontrado con ID: " + id;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUsuario(@RequestParam("id") int usuarioId) {

		Optional<Usuario> usuario = usuarioService.findById(usuarioId);
		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		usuarioService.deleteById(usuarioId);
		String mensaje = "Usuario eliminado con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
}
