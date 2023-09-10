package com.group1.dev.app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Imagenes")
public class Imagen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombreImagen;
	private String url;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="reclamo_id")
	private Reclamo reclamo;
	
	public Imagen() {

	}

	public Imagen(String nombreImagen, String url, String descripcion) {
		this.nombreImagen = nombreImagen;
		this.url = url;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Reclamo getReclamo() {
		return reclamo;
	}
	
	public void setReclamo(Reclamo reclamo) {
		this.reclamo=reclamo;
	}

	@Override
	public String toString() {
		return "Imagen [id=" + id + ", nombreImagen=" + nombreImagen + ", url=" + url + ", descripcion=" + descripcion
				+ ", reclamo=" + reclamo + "]";
	}

}
