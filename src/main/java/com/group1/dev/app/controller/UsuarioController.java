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

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.User;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.services.IUserService;
import com.group1.dev.app.services.IUsuarioService;
import com.group1.dev.app.services.UserMapper;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUserService usuarioService;
    
    @Autowired
    private UserMapper userMapper;

    
    @GetMapping(value = "/all")
	public List<UserDTO> findAll() {
		return usuarioService.findAll()
				.stream()
				.map(userMapper)
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> getUsuario(@RequestParam("id") int usuarioId) {

		Optional<UserDTO> usuario = usuarioService.findById(usuarioId)
				.map(userMapper);
		
		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrada con ID: " + usuarioId;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(usuario.get(), HttpStatus.OK);

	}
    
	@PostMapping("/add")
    public ResponseEntity<User> addUsuario(@RequestBody User usuario) {
        usuarioService.save(usuario);
        return new ResponseEntity<User>(usuario, HttpStatus.CREATED);
    }
	
	/*@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable int id, @RequestBody Usuario updatedUsuario) {
		Optional<User> existingUsuarioOptional = usuarioService.findById(id);

		if (existingUsuarioOptional.isPresent()) {
			User existingUsuario = existingUsuarioOptional.get();

			existingUsuario.setNombre(updatedUsuario.getNombre());
			existingUsuario.setDni(updatedUsuario.getDni());
			existingUsuario.setTipoPersona(updatedUsuario.getTipoPersona());

			usuarioService.save(existingUsuario);

			return ResponseEntity.ok(existingUsuario);
		} else {
			String mensaje = "Usuario no encontrado con ID: " + id;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}*/
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUsuario(@RequestParam("id") int usuarioId) {

		Optional<User> usuario = usuarioService.findById(usuarioId);
		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		usuarioService.deleteById(usuarioId);
		String mensaje = "Usuario eliminado con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
}
