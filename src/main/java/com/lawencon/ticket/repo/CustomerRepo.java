package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

	Customer findByUserId(Long id);
	
	int removeById(Long id);
}
