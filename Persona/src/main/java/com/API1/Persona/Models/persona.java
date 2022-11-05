package com.API1.Persona.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="persona")

public class persona {
	
    @Getter @Setter @Column(name= "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
	private int id;
	
    
    @Getter @Setter @Column(name= "nombre")
	private String nombre;
	
    
    @Getter @Setter @Column(name= "apellido")
	private String apellido;
	
    
    @Getter @Setter @Column(name= "dni")
	private int dni;
	
    @Getter @Setter @Column(name= "email")
	private String email;
	
    
    @Getter @Setter @Column(name= "direccion")
	private String direccion;

    

	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public int getDni() {
		return dni;
	}


	public String getEmail() {
		return email;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
    
    

}
