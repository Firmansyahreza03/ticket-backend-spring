package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

	Integer removeById(Long id);
}
