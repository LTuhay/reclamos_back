package com.group1.dev.app.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group1.dev.app.model.entity.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
    
 	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)   
    public Usuario findUser(String username, String password) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE nombreUsuario=:username", Usuario.class);
        theQuery.setParameter("username", username);

        Usuario user = theQuery.uniqueResult();

        if (user != null && checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
    
    private boolean checkPassword(String password, String passwordDB) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(password);
		System.out.println("Password: " + password);
		//System.out.println("hashedPassword: " + hashedPassword);
		System.out.println("passwordDB: " + passwordDB);
		boolean isPasswordMatch = passwordEncoder.matches(password, passwordDB);
		
		return isPasswordMatch;
	}
}
