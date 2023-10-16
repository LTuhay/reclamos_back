package com.group1.dev.app.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(
			nullable = false
			)
	private String nombre;
	
	@Column(
			nullable = false
			)
	private String email;
	
	@Column(
			nullable = false
			)
	private int dni;
	
	@Column(
			nullable = false
			)
	private int edad;
	
	@Column(
			nullable = false
			)
	private String username;
	
	@Column(
			nullable = false
			)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private TipoPersona tipoPersona;
	
	/*@ManyToOne
	@JoinColumn(name="unidad_id")
	private Unidad unidad;*/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(tipoPersona.name()));
	}
	
	

	public User() {

	}

	public User(int id, String nombre, String email, int dni, int edad, String username, String password,
			TipoPersona tipoPersona) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
		this.edad = edad;
		this.username = username;
		this.password = password;
		this.tipoPersona = tipoPersona;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
