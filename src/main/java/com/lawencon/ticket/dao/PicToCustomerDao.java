package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.PicToCustomer;

public interface PicToCustomerDao {
	PicToCustomer findById(Long id) throws Exception;

	List<PicToCustomer> findAll() throws Exception;

	PicToCustomer insert(PicToCustomer picToCustomer) throws Exception;

	PicToCustomer update(PicToCustomer picToCustomer) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
	List<PicToCustomer> findAllCustomer(Long id) throws Exception;
	
	Long findIdFromCustomer(Long id) throws Exception;
	
	PicToCustomer findByCustomerId(Long id) throws Exception;
}
