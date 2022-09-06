package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.employee.FindAllEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByIdEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByUserIdEmployeeRes;
import com.lawencon.ticket.dto.employee.InsertEmployeeReq;
import com.lawencon.ticket.dto.employee.UpdatedEmployeeReq;

public interface EmployeeService {
	FindByIdEmployeeRes findById(Long id) throws Exception;

	FindAllEmployeeRes findAll() throws Exception;

	InsertRes insert(InsertEmployeeReq employee) throws Exception;

	UpdateRes updateById(UpdatedEmployeeReq employee) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;
	
	FindByUserIdEmployeeRes findByUserId() throws Exception;
}
