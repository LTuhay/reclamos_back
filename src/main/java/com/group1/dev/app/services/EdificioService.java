package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.EdificioRepository;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;

@Service
public class EdificioService implements IEdificioService {

	@Autowired
	private EdificioRepository edificioRepo;

	@Override
	public List<Edificio> findAll() {
		return (List<Edificio>) edificioRepo.findAll();

	}

	@Override
	public Optional<Edificio> findById(int id) {
		return edificioRepo.findById(id);
	}
	
	@Override
	public Optional<Edificio> findByDireccion(String direccion) {
		return edificioRepo.findByDireccion(direccion);
	}

	@Override
	public void save(Edificio edificio) {
		edificioRepo.save(edificio);

	}

	@Override
	public void deleteById(int id) {
		edificioRepo.deleteById(id);

	}
	
	@Override
    public void addUnidad(Edificio edificio, Unidad unidad) {
    	List <Unidad> unidades = edificio.getUnidades();
    	unidades.add(unidad);
    	edificio.setUnidades(unidades);
    	unidad.setEdificio(edificio);
        edificioRepo.save(edificio);       
   }
    
	@Override
    public void delUnidad(Edificio edificio, Unidad unidad) {
    	List <Unidad> unidades = edificio.getUnidades();
    	unidades.remove(unidad);
    	edificio.setUnidades(unidades);
    	unidad.setEdificio(null);
        edificioRepo.save(edificio);       
   }

	@Override
	public List<Unidad> findUnidadesByEdificioId(Edificio edificio) {
        return edificio.getUnidades();
	}
	

}
