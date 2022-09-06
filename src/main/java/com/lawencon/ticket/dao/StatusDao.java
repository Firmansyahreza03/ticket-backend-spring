package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Status;

public interface StatusDao {

	Status findById(Long id) throws Exception;
	
	Status findByCode(String code) throws Exception;

	List<Status> findAll() throws Exception;

	Status insert(Status status) throws Exception;

	Status update(Status status) throws Exception;

	Boolean deleteById(Long id) throws Exception;
}
