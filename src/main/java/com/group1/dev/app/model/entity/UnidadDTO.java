package com.group1.dev.app.model.entity;

import java.util.List;

public class UnidadDTO {

	private int nro;
	private int piso;
	private EstadoUnidad estado;
	// atributo relacion Persona
	private List<PersonaDTO> personas;

	public UnidadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnidadDTO(int nro, int piso, EstadoUnidad estado, List<PersonaDTO> personas) {
		super();
		this.nro = nro;
		this.piso = piso;
		this.estado = estado;
		this.personas = personas;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public EstadoUnidad getEstado() {
		return estado;
	}

	public void setEstado(EstadoUnidad estado) {
		this.estado = estado;
	}

	public List<PersonaDTO> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "UnidadDTO [nro=" + nro + ", piso=" + piso + ", estado=" + estado + ", persona=" + personas + "]";
	}

}
