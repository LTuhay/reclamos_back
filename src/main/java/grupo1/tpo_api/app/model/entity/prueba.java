package grupo1.tpo_api.app.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name= "pruebas")
public class prueba {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String nombre;
	String apellido;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	Date createAt;
	
	
	public prueba() {
		super();
		// TODO Auto-generated constructor stub
	}


	public prueba(Integer id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public Integer getID() {
		return id;
	}


	public void setID(Integer id) {
		this.id = id;
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


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	@Override
	public String toString() {
		return "prueba [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", createAt=" + createAt + "]";
	}
	
}

