package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.priority.FindAllPriorityRes;
import com.lawencon.ticket.dto.priority.FindByIdPriorityRes;
import com.lawencon.ticket.dto.priority.InsertPriorityReq;
import com.lawencon.ticket.dto.priority.UpdatePriorityReq;

public interface PriorityService {
	FindByIdPriorityRes findById(Long id) throws Exception;
	
	FindAllPriorityRes findAll() throws Exception;
	
	InsertRes insert(InsertPriorityReq priority) throws Exception;
	
	UpdateRes updateById(UpdatePriorityReq priority) throws Exception;
	
	DeleteRes deleteById(Long id) throws Exception;
	
}
