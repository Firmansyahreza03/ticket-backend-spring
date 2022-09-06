package com.lawencon.ticket.dao;

import com.lawencon.ticket.model.File;

public interface FileDao {
	File findById(Long id) throws Exception;

	File insert(File file) throws Exception;

	File update(File file) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
	File findByFileName(String fileName) throws Exception;
}
