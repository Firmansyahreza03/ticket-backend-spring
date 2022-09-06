package com.lawencon.ticket.dao;

import java.util.List;

import com.lawencon.ticket.model.ThreadHeader;

public interface ThreadHeaderDao {
	ThreadHeader findById(Long id) throws Exception;
	
	List<ThreadHeader> findAll() throws Exception;
	
	List<ThreadHeader> findAllByCustomerId(Long id) throws Exception;
	
	List<ThreadHeader> findAllByPicId(Long id) throws Exception;

	ThreadHeader insert(ThreadHeader threadHeader) throws Exception;
	
	ThreadHeader updateStatus(ThreadHeader header) throws Exception;
	
	String searchByCode(String code) throws Exception;
}
