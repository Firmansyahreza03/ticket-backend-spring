package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.repo.EmployeeRepo;

@Profile(value = "springjpa")
@Repository
public class EmployeeDaoSpringJpaImpl implements EmployeeDao {

	private EmployeeRepo employeeRepo;

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee findById(Long id) throws Exception {
		return employeeRepo.findById(id).get();
	}

	@Override
	public Employee findByUserId(Long id) throws Exception {
		Employee employee = employeeRepo.findByUserId(id);
		return employee;
	}

	@Override
	public List<Employee> findAll() throws Exception {
		return employeeRepo.findAll();
	}

	@Override
	public Employee insert(Employee employee) throws Exception {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		return employeeRepo.saveAndFlush(employee);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int result = employeeRepo.removeById(id);
		return result > 0;
	}
}
