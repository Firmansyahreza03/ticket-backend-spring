package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.pictocustomer.FindAllCustPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindAllPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindByIdPtcRes;
import com.lawencon.ticket.dto.pictocustomer.InsertPtcReq;
import com.lawencon.ticket.dto.pictocustomer.UpdatePtcReq;

public interface PicToCustomerService {
	FindByIdPtcRes findById(Long id) throws Exception;

	FindAllPtcRes findAll() throws Exception;

	InsertRes insert(InsertPtcReq picToCustomer) throws Exception;

	UpdateRes updateById(UpdatePtcReq picToCustomer) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;

	FindAllCustPtcRes findAllCustomer() throws Exception;

	Long findIdFromCustomer() throws Exception;
}
