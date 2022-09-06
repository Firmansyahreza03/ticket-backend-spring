package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.ThreadDetail;

public interface ThreadDetailDao {
	ThreadDetail insertById(ThreadDetail detail) throws Exception;
	
	List<ThreadDetail> findAll(Long id) throws Exception;
}
