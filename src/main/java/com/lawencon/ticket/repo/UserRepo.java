package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	Integer removeById(Long id);
	
	User findByUserEmail(String email);
}
