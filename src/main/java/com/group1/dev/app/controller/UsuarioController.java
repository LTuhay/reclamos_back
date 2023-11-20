package com.group1.dev.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.mappers.UnidadMapper;
import com.group1.dev.app.mappers.UserMapper;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.EstadoUnidad;
import com.group1.dev.app.model.entity.TipoPersona;
import com.group1.dev.app.model.entity.Unidad;
import com.group1.dev.app.services.IUserService;
import com.group1.dev.app.services.UnidadService;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private IUserService usuarioService;

		@Autowired
	private UnidadService unidadService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UnidadMapper unidadMapper;
	

	@GetMapping(value = "/all")
	public List<UserDTO> findAll() {
		return usuarioService
				.findAll()
				.stream()
				.map(userMapper)
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> findUser(@RequestParam("user") String username) {

		Optional<UserDTO> usuario = usuarioService.findByUsername(username).map(userMapper);

		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(usuario.get(), HttpStatus.OK);

	}
	
	@PostMapping("/add")
    public ResponseEntity<?> addUsuario(@RequestBody 	EntityUser user) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(user.getUsername());
		
		if (userExists.isPresent()) {
			
			String mensaje = "Nombre de usuario existente";
			return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
			
		}
		
		
		
        usuarioService.save(user);
        return new ResponseEntity<EntityUser>(user, HttpStatus.CREATED);
    }

	@PutMapping(value = "/update/{username}")
	public ResponseEntity<?> updateUsuario(@PathVariable String username, @RequestBody UserDTO updatedUserDTO) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(username);

		if (userExists.isPresent()) {
			EntityUser updatedUser = userExists.get();
			updatedUser.setDni(updatedUserDTO.dni());
			updatedUser.setEdad(updatedUserDTO.edad());
			updatedUser.setEmail(updatedUserDTO.email());
			updatedUser.setNombre(updatedUserDTO.nombre());
			updatedUser.setTipoPersona(updatedUserDTO.tipoPersona());

			usuarioService.save(updatedUser);

			return ResponseEntity.ok("Usuario Creado");
		} else {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping(value = "/updateUnidad")
	public ResponseEntity<?> updateUsuarioUnidad(@RequestParam String username, @RequestParam Integer id_unidad) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(username);
		Optional<Unidad> unidadExists = unidadService.findById(id_unidad);

		if (userExists.isPresent() && unidadExists.isPresent()) {
			EntityUser updatedUser = userExists.get();
			Unidad updatedUnidad = unidadExists.get();

			if (updatedUser.getTipoPersona() == TipoPersona.Inquilino) {
				updatedUnidad.setEstado(EstadoUnidad.Alquilada);
			} else if (updatedUser.getTipoPersona() == TipoPersona.Propietario) {
				updatedUnidad.setEstado(EstadoUnidad.HabitadaPorDuenio);
			} else {
				String mensaje = "No se pueden asignar unidades a Administradores";
				return new ResponseEntity<String>(mensaje, HttpStatus.NOT_ACCEPTABLE);
			}

			updatedUser.setUnidad(updatedUnidad);
			usuarioService.save(updatedUser);

			return ResponseEntity.ok("Usuario actualizado");
		} else {
			String mensaje = "Usuario o unidad no encontrado: " + username + " " + id_unidad;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

		@PutMapping(value = "/removeUnidad")
	public ResponseEntity<?> removeUsuarioUnidad(@RequestParam String username) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(username);
		Optional<Unidad> unidadExists = unidadService.findById(userExists.get().getUnidad().getId());


		if (userExists.isPresent() && unidadExists.isPresent()) {
			EntityUser updatedUser = userExists.get();
			Unidad updatedUnidad = unidadExists.get();
			

			if(updatedUnidad.getPersonas().size() == 1 ){
				updatedUnidad.setEstado(EstadoUnidad.Inhabitada);
			}

			updatedUser.setUnidad(null);
			usuarioService.save(updatedUser);

			return ResponseEntity.ok("Usuario actualizado");
		} else {
			String mensaje = "Usuario o unidad no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteUsuario(@RequestParam("username") String username) {

		Optional<EntityUser> user = usuarioService.findByUsername(username);
		if (!user.isPresent()) {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		usuarioService.deleteById(user.get().getId());
		String mensaje = "Usuario eliminado con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	@GetMapping(value = "/unidad")
	public ResponseEntity<?> findUnidadByUsername(@RequestParam("user") String username) {

		Optional<EntityUser> usuario = usuarioService.findByUsername(username);

		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		
			
		Unidad unidad = usuario.get().getUnidad();

		if (unidad != null) {
			UnidadDTO unidadDTO = unidadMapper.apply(unidad);
			return new ResponseEntity<UnidadDTO>(unidadDTO, HttpStatus.OK);

		}
		
		return new ResponseEntity<String>("unidad no encontrada", HttpStatus.NOT_FOUND);

	}
}
