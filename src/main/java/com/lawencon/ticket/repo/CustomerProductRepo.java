package com.lawencon.ticket.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.CustomerProduct;

@Repository
public interface CustomerProductRepo extends JpaRepository<CustomerProduct, Long> {

	Integer removeById(Long id);
	
	List<CustomerProduct> findByCustomerUserId(Long id);
	
	CustomerProduct findByProductId(Long id);
}
