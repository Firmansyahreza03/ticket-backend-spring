package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.repo.CustomerRepo;

@Profile(value = "springjpa")
@Repository
public class CustomerDaoSpringJpaImpl implements CustomerDao {

	private CustomerRepo customerRepo;

	@Autowired
	public void setCustomerRepo(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public Customer findById(Long id) throws Exception {
		return customerRepo.findById(id).get();
	}

	@Override
	public Customer findByUserId(Long id) throws Exception {
		Customer result = customerRepo.findByUserId(id);
		return result;
	}

	@Override
	public List<Customer> findAll() throws Exception {
		return customerRepo.findAll();
	}

	@Override
	public Customer insert(Customer customer) throws Exception {
		return customerRepo.save(customer);
	}

	@Override
	public Customer update(Customer customer) throws Exception {
		return customerRepo.saveAndFlush(customer);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int result = customerRepo.removeById(id);
		return result > 0;
	}
}
