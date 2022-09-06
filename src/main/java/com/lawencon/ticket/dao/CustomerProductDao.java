package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.CustomerProduct;

public interface CustomerProductDao {
	CustomerProduct findById(Long id) throws Exception;

	List<CustomerProduct> findAll() throws Exception;

	CustomerProduct insert(CustomerProduct product) throws Exception;

	CustomerProduct update(CustomerProduct product) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
	List<CustomerProduct> findAllProduct(Long id) throws Exception;
	
	CustomerProduct findByProductId(Long id) throws Exception;
}
