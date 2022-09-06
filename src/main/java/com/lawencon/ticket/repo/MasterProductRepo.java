package com.lawencon.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.MasterProduct;

@Repository
public interface MasterProductRepo extends JpaRepository<MasterProduct, Long>{

	Integer removeById(Long id);
}
