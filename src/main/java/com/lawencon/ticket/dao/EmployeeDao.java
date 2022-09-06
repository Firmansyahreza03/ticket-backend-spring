package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Employee;

public interface EmployeeDao {
	Employee findById(Long id) throws Exception;

	List<Employee> findAll() throws Exception;

	Employee insert(Employee employee) throws Exception;

	Employee update(Employee employee) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
	Employee findByUserId(Long id) throws Exception;
}
