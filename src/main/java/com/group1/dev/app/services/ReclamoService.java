package com.group1.dev.app.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.ReclamoDTO;
import com.group1.dev.app.model.entity.TipoReclamo;

@Service
public class ReclamoService implements IReclamoService {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private ReclamoRepository reclamoRepo;
	
	
	

	@Override
	public ArrayList<ReclamoDTO> findAll() {

		ArrayList<ReclamoDTO> allReclamosDTO = new ArrayList<>();

		ArrayList<Reclamo> allReclamos = (ArrayList<Reclamo>) reclamoRepo.findAll();

		for (Reclamo reclamo : allReclamos) {

			ReclamoDTO reclamoDTO = new ReclamoDTO();

			reclamoDTO = reclamoDTO.reclamoToDto(reclamo);
			
			allReclamosDTO.add(reclamoDTO);

		}

		
		return allReclamosDTO;
	}

	@Override
	public ReclamoDTO findById(int id) {
		
		Optional<Reclamo> optionalReclamo = Optional.of(new Reclamo());
		optionalReclamo = reclamoRepo.findById(id);
		Reclamo reclamo = new Reclamo();
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		if (optionalReclamo.isPresent()) {
			
			reclamo = optionalReclamo.get();
		}
		
		reclamoDTO = reclamoDTO.reclamoToDto(reclamo);
		
		return reclamoDTO;
	}

	@Override
	public void save(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = new Reclamo();

		reclamo = reclamoDTO.DTOtoReclamo();
		reclamoRepo.save(reclamo);

	}

	@Override
	public void deleteById(int id) {
		reclamoRepo.deleteById(id);

	}

	public void update(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = new Reclamo();
		reclamo = reclamoDTO.DTOtoReclamo();
		reclamoRepo.saveAndFlush(reclamo);
	}

	public ArrayList<ReclamoDTO> filter(int userId, int buildingId, String state, String type) {

		Reclamo reclamo = new Reclamo();

		if (userId != 0) {

			Optional<Persona> optionalPersona = Optional.of(new Persona());
			optionalPersona = personaService.findById(userId);
			Persona persona = new Persona();
			if (optionalPersona.isPresent()) {

				persona = optionalPersona.get();
			}
			reclamo.setPersona(persona);

		}
		if (buildingId != 0) {
			Optional<Edificio> optionalEdificio = Optional.of(new Edificio());
			optionalEdificio = edificioService.findById(buildingId);
			Edificio edificio = new Edificio();
			if (optionalEdificio.isPresent()) {

				edificio = optionalEdificio.get();
			}
			reclamo.setEdificio(edificio);
		}
		if (state != null) {
			EstadoReclamo estado = EstadoReclamo.valueOf(state);

			reclamo.setEstadoReclamo(estado);
		}
		if (type != null) {
			TipoReclamo tipo = TipoReclamo.valueOf(type);
			reclamo.setTipoReclamo(tipo);

		}
		Example<Reclamo> example = Example.of(reclamo);
		ArrayList<Reclamo> allReclamos = (ArrayList<Reclamo>) reclamoRepo.findAll(example);
		
		ArrayList<ReclamoDTO> allReclamosDTO = new ArrayList<>();
		
		
		for (Reclamo reclamoIt : allReclamos) {

			ReclamoDTO reclamoDTO = new ReclamoDTO();

			reclamoDTO = reclamoDTO.reclamoToDto(reclamoIt);
			
			allReclamosDTO.add(reclamoDTO);

		}

		
		return allReclamosDTO;

	}
}
