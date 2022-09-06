package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.Role;

public interface RoleDao {

	Role findById(Long id) throws Exception;

	List<Role> findAll() throws Exception;

	Role insert(Role role) throws Exception;

	Role update(Role role) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
	Role findByRoleCode(String roleCode) throws Exception;

}
