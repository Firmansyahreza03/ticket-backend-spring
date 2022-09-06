package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.masterproduct.FindAllMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.FindByIdMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.InsertMasterProductReq;
import com.lawencon.ticket.dto.masterproduct.UpdateMasterProductReq;

public interface MasterProductService {
	FindByIdMasterProductRes findById(Long id) throws Exception;
	
	FindAllMasterProductRes findAll() throws Exception;
	
	InsertRes insert(InsertMasterProductReq masterProduct) throws Exception;
	
	UpdateRes updateById(UpdateMasterProductReq masterProduct) throws Exception;
	
	DeleteRes deleteById(Long id) throws Exception;
}
