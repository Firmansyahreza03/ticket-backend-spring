package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	Integer removeById(Long id);
	
	Employee findByUserId(Long id);
}
