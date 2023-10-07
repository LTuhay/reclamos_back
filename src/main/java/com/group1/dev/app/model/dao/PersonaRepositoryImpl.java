package com.group1.dev.app.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group1.dev.app.model.entity.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PersonaRepositoryImpl implements PersonaRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true) 
	public Persona findPersonadni(int dni) {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Persona> theQuery = currentSession.createQuery("FROM Persona WHERE dni=:dni", Persona.class);
        theQuery.setParameter("dni", dni);

        Persona persona = theQuery.uniqueResult();

        if (persona != null) {
            return persona;
        } else {
            return null;
        }
	}


}
