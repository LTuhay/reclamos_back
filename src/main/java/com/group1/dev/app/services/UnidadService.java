package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.UnidadRepository;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class UnidadService implements IUnidadService {

	@Autowired
	private UnidadRepository unidadRepo;
	

	@Override
	public List<Unidad> findAll() {
		return (List<Unidad>) unidadRepo.findAll();
	}

	@Override
	public Optional<Unidad> findById(int id) {
		return unidadRepo.findById(id);
	}
	

		
	@Override
	public void save(Unidad unidad) {
		unidadRepo.save(unidad);
	}

	@Override
	public void deleteById(int id) {
		unidadRepo.deleteById(id);
	}
	
    public void addPersona(Unidad unidad, Persona persona) {
    	 List <Persona> personas = unidad.getPersonas();
    	 personas.add(persona);
         unidad.setPersonas(personas);
         persona.setUnidad(unidad);
         unidadRepo.save(unidad);       
    }

    public void delPersona(Unidad unidad, Persona persona) {
   	 	 List <Persona> personas = unidad.getPersonas();
   	 	 personas.remove(persona);
   	 	 unidad.setPersonas(personas);
   	 	 persona.setUnidad(null);
         unidadRepo.save(unidad);         
    }
    
    public List<Persona> findPersonasByUnidadId(Unidad unidad) {
          return unidad.getPersonas();
          
    }
    

}


