package com.group1.dev.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group1.dev.app.model.entity.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PersonaDAOimpl implements IPersonaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Persona> getQuery = currentSession.createQuery("from Persona", Persona.class);
		List<Persona> personas = getQuery.getResultList();

		return personas;
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(int id) {

		Session currentSession = entityManager.unwrap(Session.class);
		Persona persona = currentSession.get(Persona.class, id);
		// TODO Auto-generated method stub
		return persona;
	}

	@Override
	@Transactional
	public void save(Persona persona) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(persona);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Persona where id=:idPersona");
		theQuery.setParameter("idPersona", id);
		theQuery.executeUpdate();
	}

}
