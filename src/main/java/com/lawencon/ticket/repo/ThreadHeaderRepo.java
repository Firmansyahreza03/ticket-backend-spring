package com.lawencon.ticket.repo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.ThreadHeader;

@Repository
public interface ThreadHeaderRepo extends JpaRepository<ThreadHeader, Long>{

	List<ThreadHeader> findByPicToCustomerCustomerUserIdOrderByPriorityPriorityPointDesc(Long id);
	
	List<ThreadHeader> findByPicToCustomerPicUserIdOrderByPriorityPriorityPointDesc(Long id);
	
	@Query(value = "SELECT code FROM ThreadHeader WHERE code=:code")
	String findCodeByCode(@PathParam("code") String code);
}
