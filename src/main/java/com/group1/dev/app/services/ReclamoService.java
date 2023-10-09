package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
<<<<<<< Updated upstream
import com.group1.dev.app.model.entity.Reclamo;

=======
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.TipoReclamo;
>>>>>>> Stashed changes

@Service
public class ReclamoService implements IReclamoService {

	@Autowired
	private ReclamoRepository reclamoRepo;

	@Override
<<<<<<< Updated upstream
	public List<Reclamo> findAll() {
		return (List<Reclamo>) reclamoRepo.findAll();
	}

	@Override
	public Optional<Reclamo> findById(int id) {
		return reclamoRepo.findById(id);
=======
	public ArrayList<Reclamo> findAll() {

		ArrayList<Reclamo> allReclamos = (ArrayList<Reclamo>) reclamoRepo.findAll();

		return allReclamos;
	}

	@Override
	public Reclamo findById(int id) {

		Optional<Reclamo> optionalReclamo = Optional.of(new Reclamo());
		optionalReclamo = reclamoRepo.findById(id);
		Reclamo reclamo = new Reclamo();

		if (optionalReclamo.isPresent()) {

			reclamo = optionalReclamo.get();
		}

		return reclamo;
>>>>>>> Stashed changes
	}

	@Override
	public void save(Reclamo reclamo) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
		reclamoRepo.save(reclamo);

	}

	@Override
	public void deleteById(int id) {
		reclamoRepo.deleteById(id);

	}

<<<<<<< Updated upstream
=======
	public void update(Reclamo reclamoNew, Reclamo reclamoOld) {
		reclamoNew.setId(reclamoOld.getId());

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

	public EstadoReclamo getEstadoReclamo(String state) {

		for (EstadoReclamo estado : EstadoReclamo.values()) {
			if (estado.name().equals(state)) {
				return estado;
			}
		}

		return null;

	}

>>>>>>> Stashed changes
}
