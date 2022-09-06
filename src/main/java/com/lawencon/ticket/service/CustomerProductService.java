package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.customerproduct.FindAllByCustCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindAllCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindByIdCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.InsertCustomerProductReq;
import com.lawencon.ticket.dto.customerproduct.UpdatedByIdCustomerProductReq;

public interface CustomerProductService {
	FindByIdCustomerProductRes findById(Long id) throws Exception;

	FindAllCustomerProductRes findAll() throws Exception;

	InsertRes insert(InsertCustomerProductReq customerProduct) throws Exception;

	UpdateRes updateById(UpdatedByIdCustomerProductReq customerProduct) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;
	
	FindAllByCustCustomerProductRes findAllProductByCust() throws Exception;

}
