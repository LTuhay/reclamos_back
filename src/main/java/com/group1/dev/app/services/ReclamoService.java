package com.group1.dev.app.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Reclamo;
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
	public ArrayList<Reclamo> findAll() {

		return (ArrayList<Reclamo>) reclamoRepo.findAll();
	}

	@Override
	public Reclamo findById(Integer id) {

		Optional<Reclamo> optionalReclamo = Optional.of(new Reclamo());
		optionalReclamo = reclamoRepo.findById(id);
		Reclamo reclamo = new Reclamo();

		if (optionalReclamo.isPresent()) {

			reclamo = optionalReclamo.get();
		}

		return reclamo;
	}

	@Override
	public void save(Reclamo reclamo) {

		reclamoRepo.save(reclamo);

	}

	@Override
	public void deleteById(Integer id) {
		reclamoRepo.deleteById(id);

	}

	public void update(Integer id, Reclamo reclamoNew) {
		reclamoNew.setId(id);

		reclamoRepo.save(reclamoNew);
	}

	public ArrayList<Reclamo> filter(Integer userId, Integer buildingId, String state, String type) {

		Reclamo reclamo = new Reclamo();

		if (userId != null) {
			reclamo.setPersona(personaService.findById(userId).get());
		}
		if (buildingId != null) {
			reclamo.setEdificio(edificioService.findById(buildingId).get());
		}
		if (state != null) {
			reclamo.setEstadoReclamo(EstadoReclamo.valueOf(state));
		} else {
			reclamo.setEstadoReclamo(null);
		}
		if (type != null) {
			TipoReclamo tipo = TipoReclamo.valueOf(type);

			reclamo.setTipoReclamo(tipo);
		}
		Example<Reclamo> example = Example.of(reclamo);
		System.out.println(example);
		ArrayList<Reclamo> allReclamos = (ArrayList<Reclamo>) reclamoRepo.findAll(example);

		System.out.println(allReclamos);
		return allReclamos;

	}

}
