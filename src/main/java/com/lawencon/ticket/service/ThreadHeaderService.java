package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.threadhdr.FindAllThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.FindByIdThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.InsertThreadHdrReq;
import com.lawencon.ticket.dto.threadhdr.UpdateStatusThreadHdrReq;

public interface ThreadHeaderService {
	FindByIdThreadHdrRes findById(Long id) throws Exception;
	
	FindAllThreadHdrRes findAll() throws Exception;

	InsertRes insert(InsertThreadHdrReq threadHeader) throws Exception;

	FindAllThreadHdrRes findAllByCustomerId() throws Exception;

	UpdateRes updateStatus(UpdateStatusThreadHdrReq header) throws Exception;
	
	FindAllThreadHdrRes findAllByPicId() throws Exception;
}
