package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Company;

public interface CompanyDao {
	Company findById(Long id) throws Exception;
	
	List<Company> findAll() throws Exception;
	
	Company insert(Company company) throws Exception;
	
	Company update(Company company) throws Exception;
	
	Boolean deleteById(Long id) throws Exception;
}
