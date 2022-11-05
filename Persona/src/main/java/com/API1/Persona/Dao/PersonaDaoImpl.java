package com.API1.Persona.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import com.API1.Persona.Models.persona;


@Repository
@Transactional

public class PersonaDaoImpl implements PersonaDao {
	
    @PersistenceContext
   private EntityManager entityManager;

	

    
	@Override
	public persona getPersona(int dni) {
		persona p = new persona();
		try {
			String query = "From persona where dni = "+ dni;

			
			p = (persona) entityManager.createQuery(query).getSingleResult();
			
			return p;
		}
		catch(Exception e) {
			System.out.println("Error al buscar en tabla persona");
			
			return p;
		}

	}




	@Override
	public String Eliminar(int dni) {
		
			persona p = new persona();		
			
 		try {		
 	        String query = "from persona where dni = "+ dni;
 	        
 	        p =  (persona) entityManager.createQuery(query).getSingleResult();
 	        
 	         entityManager.remove(p);
 	         
 	        
		}
		catch(Exception e) {
			System.out.println("Error al buscar en tabla persona");
			
			return "ERROR";
		}
         
         
         return "OK";
	}




	@Override
	public String agregarP(persona pp) {
		
		try {
			
			entityManager.merge(pp);
			
			return "OK";
			
		}
		catch(Exception e) {
			System.out.println("Error al agregar persona");
			return "ERROR";
		}
		
	}




	@Override
	public String actualizarP(persona per) {
		
		try {
			
			String query = "Select id From persona where dni = "+ per.getDni();

			
			String id = entityManager.createQuery(query).getSingleResult().toString();
			
			per.setId(Integer.parseInt(id));
			
			if(per.getId() != 0 && per.getNombre() != null) {
				
	            String query2 = "Update persona set nombre = '"+per.getNombre()+"', apellido = '"+ per.getApellido()+"', dni = "+per.getDni()+", email = '"+per.getEmail()+"', direccion = '" + per.getDireccion()+"' where id = " + per.getId() + "";
	            
	            entityManager.createQuery(query2).executeUpdate();
				
	            return "OK";
			}
			else {
				System.out.println("Error al actualiar persona: " + per.getNombre());
				return "ERROR";
			}
			
			
			
		}
		catch(Exception e) {
			System.out.println("Error al actualizar datos de  persona");
			return "ERROR";
		}
		
	}




	@Override
	public persona loginP(persona p) {
		
		
        String query = "From persona where nombre ='" + p.getNombre()+ "' and dni = '" + p.getDni() + "'";

        List<persona> result =  entityManager.createQuery(query).getResultList();

        if(result.isEmpty()){
            return null;
        }
        else {
            return result.get(0);
        }
		
	}
	


}
