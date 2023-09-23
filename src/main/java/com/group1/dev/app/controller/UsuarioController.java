package com.group1.dev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.model.entity.Usuario;
import com.group1.dev.app.model.entity.UsuarioDTO;
import com.group1.dev.app.services.IUsuarioService;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    private EntityManager entityManager;
    
	@PostMapping("/add")
    public ResponseEntity<UsuarioDTO> addPersona(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        UsuarioDTO usuarioDTO = convertToDTO(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.CREATED);
    }
    
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNombre(), 
            usuario.getApellido(), 
            usuario.getDni(),
            usuario.getTipoPersona(), 
            usuario.getNombreUsuario(), 
            usuario.getPassword());
        return usuarioDTO;
    }
    
	private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = entityManager.unwrap(Usuario.class);
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellido(usuarioDTO.getApellido());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setTipoPersona(usuarioDTO.getTipoPersona());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setPassword(usuarioDTO.getPassword());
        return usuario;
	}
}
