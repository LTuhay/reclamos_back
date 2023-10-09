package com.group1.dev.app.model.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.dev.app.model.entity.Reclamo;


@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo,Integer> {
	
	
	

}
