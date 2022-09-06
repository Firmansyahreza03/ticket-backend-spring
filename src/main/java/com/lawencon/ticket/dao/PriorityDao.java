package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Priority;

public interface PriorityDao {

		Priority findById(Long id) throws Exception;
		
		List<Priority> findAll() throws Exception;
		
		Priority insert(Priority priority) throws Exception;
		
		Priority update(Priority priority) throws Exception;
		
		Boolean deleteById(Long id) throws Exception;
}
