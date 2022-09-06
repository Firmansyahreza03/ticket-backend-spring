package com.lawencon.ticket.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.model.ThreadDetail;

@Repository
public interface ThreadDetailRepo extends JpaRepository<ThreadDetail, Long>{

	List<ThreadDetail> findByHeaderId(Long id);
}
