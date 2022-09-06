package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.User;

public interface UserDao {
	
	User findById(Long id) throws Exception;
	
	List<User> findAll() throws Exception;
	
	User insert(User user) throws Exception;
	
	User update(User user) throws Exception;
	
	Boolean deleteById(Long id) throws Exception;
	
	User findByEmail(String email) throws Exception;
}
