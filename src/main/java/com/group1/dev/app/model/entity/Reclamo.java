package com.group1.dev.app.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reclamos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reclamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_reclamo;
	private String titulo;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private EntityUser user;
	private String descripcion;
	@Column(nullable = false)
	private EstadoReclamo estadoReclamo;
	@Column(nullable = false)
	private TipoReclamo tipoReclamo;
	private String actualizacion;

	@OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
	private List<Imagen> fotos = new ArrayList<Imagen>();

	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;

	public Reclamo() {

	}

	public int getId() {
		return id_reclamo;
	}

	public void setId(int id) {
		this.id_reclamo = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoReclamo getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public List<Imagen> getFotos() {
		return fotos;
	}

	public void setFotos(List<Imagen> fotos) {
		this.fotos = fotos;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	@Override
	public String toString() {
		return "Reclamo [id=" + id_reclamo + ", titulo=" + titulo + ", user=" + user + ", descripcion=" + descripcion
				+ ", estadoReclamo=" + estadoReclamo + ", tipoReclamo=" + tipoReclamo + ", actualizacion="
				+ actualizacion + ", fotos=" + fotos + ", edificio=" + edificio + "]";
	}

}
