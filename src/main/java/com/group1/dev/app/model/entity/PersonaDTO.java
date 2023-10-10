package com.group1.dev.app.model.entity;

public class PersonaDTO {

	private String nombre;
	private String apellido;
	private int dni;
	private TipoPersona tipoPersona;
	// atributos unidad
	private UnidadDTO unidad;

	public PersonaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonaDTO(String nombre, String apellido, int dni, TipoPersona tipoPersona, UnidadDTO unidad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.tipoPersona = tipoPersona;
		this.unidad = unidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public UnidadDTO getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadDTO unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		return "PersonaDTO [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", tipoPersona="
				+ tipoPersona + ", unidad=" + unidad + "]";
	}

}
