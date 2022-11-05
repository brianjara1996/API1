package com.API1.Persona.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.API1.Persona.Dao.PersonaDao;
import com.API1.Persona.Models.persona;
import com.API1.Persona.Utils.ErrorMensaje;
import com.API1.Persona.Utils.JWTUtil;

@RestController
public class personaController {

	@Autowired
	private PersonaDao personaDao;
	
    @Autowired
    private JWTUtil jwtUtil;
	
	
	
    @RequestMapping(value = "api/persona/{dni}", method = RequestMethod.GET)
    public Object getPersona(@PathVariable int dni, @RequestHeader(value="Authorization") String token){
   	
    	
    	
    	String verifico = jwtUtil.getKey(token);
    	
    	if(verifico == null) {
    		
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(500);
    		er.setMensaje("Token recibido no valido para continuar");
    		
    		return er;
    	}
    	else {
    		
        	persona p = new persona();
        	
        	p = personaDao.getPersona(dni);
        	
        	if(p.getId() == 0 && p.getNombre() == null) {
        		
        		ErrorMensaje er = new ErrorMensaje();
        		er.setCode(500);
        		er.setMensaje("No se encontro persona con ese DNI en la Base de datos");
        		
        		return er;
        	}
        	else {
        	  return p;
        	}
    	}
    	    	
    }
    
    
    @RequestMapping(value = "api/persona-delete/{dni}" , method = RequestMethod.GET)
    public Object Eliminar(@PathVariable int dni , @RequestHeader(value="Authorization") String token){
   	
    	
    	
    	String verifico = jwtUtil.getKey(token);
    	
    	if(verifico == null) {
    		
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(500);
    		er.setMensaje("Token recibido no valido para continuar");
    		
    		return er;
    	}
    	else {
    		
        	String resp =  personaDao.Eliminar(dni);
        	
        	if(resp == "OK") {
        		
        		ErrorMensaje er = new ErrorMensaje();
        		er.setCode(200);
        		er.setMensaje("Se Elimino correctamente");
        		
        		return er;
        		
        	}
        	else {
        	
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(200);
    		er.setMensaje("No se encontro persona para eliminar en DB");
    		
    		return er;
        	
        	}
    	}
    		
    }
    
	
    @RequestMapping(value = "api/persona-add" , method = RequestMethod.POST)
    public Object agregarP(@RequestBody persona pp , @RequestHeader(value="Authorization") String token){
   	
    	
    	
    	String verifico = jwtUtil.getKey(token);
    	
    	
    	if(verifico == null) {
    		
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(500);
    		er.setMensaje("Token recibido no valido para continuar");
    		
    		return er;
    		
    	}
    	else {
    		
    		
    		String resu = personaDao.agregarP(pp);
    		
             if(resu == "OK") {
        		
        		ErrorMensaje er = new ErrorMensaje();
        		er.setCode(200);
        		er.setMensaje("Se agrego correctamente");
        		
        		return er;
        		
        	}
        	else {
        	
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(500);
    		er.setMensaje("Error al agregar persona a la DB");
    		
    		return er;
        	
        	}
    		
    	}

    }
    
    
    @RequestMapping(value = "api/persona-update" , method = RequestMethod.POST)
    public Object actulizarP(@RequestBody persona pp , @RequestHeader(value="Authorization") String token){
   	
    	
    	
    	String verifico = jwtUtil.getKey(token);
    	
    	
    	if(verifico == null) {
    		
    		ErrorMensaje er = new ErrorMensaje();
    		er.setCode(500);
    		er.setMensaje("Token recibido no valido para continuar");
    		
    		return er;
    		
    	}
    	else {
    	
    		String resp = personaDao.actualizarP(pp);
    		
    		 if(resp == "OK") {
         		
         		ErrorMensaje er = new ErrorMensaje();
         		er.setCode(200);
         		er.setMensaje("Se agrego correctamente");
         		
         		return er;
         		
         	}
         	else {
         	
     		ErrorMensaje er = new ErrorMensaje();
     		er.setCode(500);
     		er.setMensaje("Error al agregar persona a la DB");
     		
     		return er;
         	
         	}
    		
    	}
    }
    
    
    @RequestMapping(value = "api/Login" , method = RequestMethod.POST)
    public String loginP(@RequestBody persona per){

     persona pp = personaDao.loginP(per);
     
     if(pp.getId() !=  0 && pp.getDni() != 0) {
    	 
    	
    	 String dni = String.valueOf(per.getDni());
    	 
    	 String Token = jwtUtil.create(dni , per.getNombre());
    	 
    	 return Token;
     } 
     
     return "FAIL";

    }
}
