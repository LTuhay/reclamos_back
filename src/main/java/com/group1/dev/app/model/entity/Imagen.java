package com.group1.dev.app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Imagenes")
public class Imagen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombreImagen;
	private String descripcion;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] datosImagen;

	@ManyToOne
	@JoinColumn(name="reclamo_id")
	private Reclamo reclamo;
	
	public Imagen() {

	}

	public Imagen(byte[] datosImagen) {
		super();
		this.datosImagen = datosImagen;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getDatosImagen() {
		return datosImagen;
	}

	public void setDatosImagen(byte[] datosImagen) {
		this.datosImagen = datosImagen;
	}
	
	public Reclamo getReclamo() {
		return reclamo;
	}
	
	public void setReclamo(Reclamo reclamo) {
		this.reclamo=reclamo;
	}

	@Override
	public String toString() {
		return "Imagen [id=" + id + ", nombreImagen=" + nombreImagen  + ", descripcion=" + descripcion
				+ ", reclamo=" + reclamo + "]";
	}

}
