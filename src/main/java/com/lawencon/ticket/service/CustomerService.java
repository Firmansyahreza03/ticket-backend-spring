package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.customer.FindAllCustomerRes;
import com.lawencon.ticket.dto.customer.FindByIdCustomerRes;
import com.lawencon.ticket.dto.customer.FindByUserIdCustomerRes;
import com.lawencon.ticket.dto.customer.InsertCustomerReq;
import com.lawencon.ticket.dto.customer.UpdateCustomerReq;

public interface CustomerService {
	FindByIdCustomerRes findById(Long id) throws Exception;

	FindAllCustomerRes findAll() throws Exception;

	InsertRes insert(InsertCustomerReq customer) throws Exception;

	UpdateRes updateById(UpdateCustomerReq customer) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;
	
	FindByUserIdCustomerRes findByUserId() throws Exception;
}
