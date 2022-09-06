package com.lawencon.ticket.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.PicToCustomer;

@Repository
public interface PicToCustomerRepo extends JpaRepository<PicToCustomer, Long> {

	Integer removeById(Long id);

	@Query(value = " SELECT tptc.id FROM PicToCustomer tptc  WHERE tptc.customer.id = :id ")
	Long findIdFromCustomer(Long id);
	
	List<PicToCustomer> findByPicUserId(Long id);
	
	PicToCustomer findByCustomerId(Long id);
}
