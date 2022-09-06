package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long> {

	int removeById(Long id);
	
	File findByFileName(String fileName);
}
