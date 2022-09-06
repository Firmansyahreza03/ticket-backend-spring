package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

	Integer removeById(Long id);
	
	Role findByRoleCode(String roleCode);
}
