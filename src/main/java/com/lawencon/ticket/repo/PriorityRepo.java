package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Priority;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long>{

	Integer removeById(Long id);
}
