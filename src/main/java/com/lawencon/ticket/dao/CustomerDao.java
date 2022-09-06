package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Customer;

public interface CustomerDao {

	Customer findById(Long id) throws Exception;

	List<Customer> findAll() throws Exception;

	Customer insert(Customer customer) throws Exception;

	Customer update(Customer customer) throws Exception;

	Boolean deleteById(Long id) throws Exception;

	Customer findByUserId(Long id) throws Exception;
}
