package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.status.FindAllStatusRes;
import com.lawencon.ticket.dto.status.FindByCodeStatusRes;
import com.lawencon.ticket.dto.status.FindByIdStatusRes;
import com.lawencon.ticket.dto.status.InsertStatusReq;
import com.lawencon.ticket.dto.status.UpdateStatusReq;

public interface StatusService {
	FindByIdStatusRes findById(Long id) throws Exception;
	
	FindByCodeStatusRes findByCode(String code) throws Exception;

	FindAllStatusRes findAll() throws Exception;
	
	InsertRes insert(InsertStatusReq statusReq) throws Exception;

	UpdateRes updateById(UpdateStatusReq status) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;

}
