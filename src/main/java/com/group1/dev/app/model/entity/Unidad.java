package com.group1.dev.app.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Unidades")
public class Unidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private int nro;
    private int piso;
    private EstadoUnidad estado;
    
    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<EntityUser> personas = new ArrayList<EntityUser>();
    
    
    @ManyToOne
    @JoinColumn(name = "Edificio_id")
    private Edificio edificio;
     
    public Unidad() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void addPersona(EntityUser persona) {
		personas.add(persona);
	}
	
	public List<EntityUser> getPersonas() {
		return personas;
	}
	
	public void delPersona(EntityUser persona) {
		personas.remove(persona);
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	@Override
	public String toString() {
		return "Unidad [id=" + id + ", nro=" + nro + ", piso=" + piso + ", estado=" + estado + ", personas=" + personas
				+ ", edificio=" + edificio + "]";
	}

}
