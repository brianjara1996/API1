package com.API1.Persona.Dao;

import org.springframework.web.bind.annotation.PathVariable;

import com.API1.Persona.Models.persona;


public interface PersonaDao {

	persona getPersona(@PathVariable int dni);
	
	String Eliminar(@PathVariable int dni);
	
	String agregarP(persona pp);
	
	String actualizarP(persona pp);
	
	persona loginP(persona p);
}
