package com.group1.dev.app.model.entity;

public class UsuarioDTO {
    
    private String nombre;
    private String apellido;
    private int dni;
	private TipoPersona tipoPersona;
    private String nombreUsuario;
    private String password;
    

    public UsuarioDTO(String nombre, String apellido, int dni, TipoPersona tipoPersona, String nombreUsuario, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tipoPersona = tipoPersona;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
	
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
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

       public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", password=" + password + "]";
    }
}

