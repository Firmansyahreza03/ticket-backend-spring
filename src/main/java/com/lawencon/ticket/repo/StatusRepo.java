package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long>{

	Integer removeById(Long id);
	
	Status findByStatusCode(String code);
}
