package com.group1.dev.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.TipoReclamo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class ReclamoService implements IReclamoService {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEdificioService edificioService;

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

	public List<Reclamo> filter(Integer userId, Integer buildingId, String state, String type) {

		Reclamo reclamo = new Reclamo();

		if (userId != null) {
			reclamo.setUser(userService.findById(userId).get());
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


		Example<Reclamo> example = Example.of(reclamo,
			    ExampleMatcher.matching()
			        .withIgnorePaths("user_id"));
		System.out.println(example);
		
				
		
		List<Reclamo> allReclamos = reclamoRepo.findAll(example);

		System.out.println(allReclamos);
		return allReclamos;

	}
	
	public List<Reclamo> filter2(Integer userId, Integer buildingId, EstadoReclamo state, TipoReclamo type) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Reclamo> criteriaQuery = criteriaBuilder.createQuery(Reclamo.class);
	    Root<Reclamo> root = criteriaQuery.from(Reclamo.class);

	    List<Predicate> predicates = new ArrayList<>();

	    if (userId != null) {
	        predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
	    }
	    if (buildingId != null) {
	        predicates.add(criteriaBuilder.equal(root.get("edificio").get("id"), buildingId));
	    }
	    if (state != null) {
	        predicates.add(criteriaBuilder.equal(root.get("estadoReclamo"), state));
	    }
	    if (type != null) {
	        predicates.add(criteriaBuilder.equal(root.get("tipoReclamo"), type));
	    }

	    criteriaQuery.where(predicates.toArray(new Predicate[0]));

	    return entityManager.createQuery(criteriaQuery).getResultList();
	}

	
}
