package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.company.FindAllCompanyRes;
import com.lawencon.ticket.dto.company.FindByIdCompanyRes;
import com.lawencon.ticket.dto.company.InsertCompanyReq;
import com.lawencon.ticket.dto.company.UpdateCompanyReq;

public interface CompanyService {
	FindByIdCompanyRes findById(Long id) throws Exception;
	
	FindAllCompanyRes findAll() throws Exception;
	
	InsertRes insert(InsertCompanyReq company) throws Exception;
	
	UpdateRes updateById(UpdateCompanyReq company) throws Exception;
	
	DeleteRes deleteById(Long id) throws Exception;
}
