package com.group1.dev.app.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group1.dev.app.model.entity.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	@Transactional(readOnly = true)
	public Persona findPersonaDni(int dni) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);

        Root<Persona> personaRoot = cq.from(Persona.class);
        cq.select(personaRoot);
        cq.where(cb.equal(personaRoot.get("dni"), dni));
		
        TypedQuery<Persona> query = entityManager.createQuery(cq);
        
        try {
        return query.getSingleResult();
        }
        catch(NoResultException e) {
        	return null;
        }
       
      
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findPersonaApellido(String apellido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);

        Root<Persona> personaRoot = cq.from(Persona.class);
        cq.select(personaRoot);
        cq.where(cb.equal(personaRoot.get("apellido"), apellido));

        TypedQuery<Persona> query = entityManager.createQuery(cq);
        return query.getResultList();
	}

}
