package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.MasterProduct;

public interface MasterProductDao {
	MasterProduct findById(Long id) throws Exception;
	
	List<MasterProduct> findAll() throws Exception;
	
	MasterProduct insert(MasterProduct masterProduct) throws Exception;
	
	MasterProduct update(MasterProduct masterProduct) throws Exception;
	
	Boolean deleteById(Long id) throws Exception;
}
