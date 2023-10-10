package com.group1.dev.app.model.entity;

import java.util.Optional;



import com.group1.dev.app.services.EdificioService;
import com.group1.dev.app.services.PersonaService;



public class ReclamoDTO {
	
	private int id_reclamo;
	private String titulo;
	private int id_persona = 0;
	private String descripcion;
	private String estadoReclamo;
	private String tipoReclamo;
	private String actualizacion;
	private int id_edificio = 0;
	
	
	public ReclamoDTO() {
		super();
	}

	public ReclamoDTO(int id_reclamo, String titulo, int id_persona, String descripcion, String estadoReclamo,
			String tipoReclamo, String actualizacion, int id_edificio) {
		super();
		this.id_reclamo = id_reclamo;
		this.titulo = titulo;
		this.id_persona = id_persona;
		this.descripcion = descripcion;
		this.estadoReclamo = estadoReclamo;
		this.tipoReclamo = tipoReclamo;
		this.actualizacion = actualizacion;
		this.id_edificio = id_edificio;
	}
	
	

	public int getId_reclamo() {
		return id_reclamo;
	}

	public void setId_reclamo(int id_reclamo) {
		this.id_reclamo = id_reclamo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(String estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public String getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public int getId_edificio() {
		return id_edificio;
	}

	public void setId_edificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
	public Reclamo DTOtoReclamo() {
		
		Reclamo reclamo = new Reclamo();
		
		if (this.actualizacion != null) { 
		reclamo.setActualizacion(this.actualizacion);
		}
		
		if(this.descripcion != null) {
		reclamo.setDescripcion(this.descripcion);}
		
		
		if(this.id_edificio !=0) {
		Optional<Edificio> optionalEdificio = Optional.of(new Edificio());
		EdificioService edificioService = new EdificioService();
		optionalEdificio = edificioService.findById(this.id_edificio);
		Edificio edificio = new Edificio();
    	if (optionalEdificio.isPresent()) {
    	    
    	    edificio = optionalEdificio.get();
    	}
    	
		
		reclamo.setEdificio(edificio);}
		
		if (this.id_persona != 0) {
		Optional<Persona> optionalPersona = Optional.of(new Persona());
		PersonaService personaService = new PersonaService();
		optionalPersona = personaService.findById(this.id_persona);
		Persona persona = new Persona();
    	if (optionalPersona.isPresent()) {
    	    
    	    persona = optionalPersona.get();
    	}
		
		reclamo.setPersona(persona);
		}
		
		if (this.estadoReclamo != null) {
		
		EstadoReclamo estado = EstadoReclamo.valueOf(this.estadoReclamo);
		reclamo.setEstadoReclamo(estado);
		}
		
		if (this.tipoReclamo != null) {
		TipoReclamo tipo = TipoReclamo.valueOf(this.tipoReclamo);
		reclamo.setTipoReclamo(tipo);}
		
		if (this.titulo != null) {
		reclamo.setTitulo(this.titulo);
		}
		
		
		
		
		return reclamo;
		
	}
	
	public ReclamoDTO reclamoToDto(Reclamo reclamo) {
		
		
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		
		if (reclamo.getActualizacion() != null) { 
		reclamoDTO.setActualizacion(reclamo.getActualizacion());
		}
		
		if(reclamo.getDescripcion() != null) {
		reclamoDTO.setDescripcion(reclamo.getDescripcion());}
		
		Edificio edificio = new Edificio();
		edificio = reclamo.getEdificio();
		if(edificio.getId() !=0) {
			reclamoDTO.setId_edificio(edificio.getId());
		}
		
		Persona persona = new Persona();
		persona = reclamo.getPersona();
		if(persona.getId() !=0) {
			reclamoDTO.setId_persona(persona.getId());
		}
		
		if (reclamo.getEstadoReclamo() != null) {
		
		String estado = reclamo.getEstadoReclamo().toString();
		reclamoDTO.setEstadoReclamo(estado);
		}
		
		if (reclamo.getTipoReclamo() != null) {
		String tipo = reclamo.getTipoReclamo().toString();
		reclamoDTO.setTipoReclamo(tipo);}
		
		if (reclamo.getTitulo() != null) {
		reclamoDTO.setTitulo(reclamo.getTitulo());
		}		
		return reclamoDTO;
	}
	
}
