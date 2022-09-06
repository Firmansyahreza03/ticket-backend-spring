package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.threaddtl.FindAllThreadDtlRes;
import com.lawencon.ticket.dto.threaddtl.InsertThreadDtlReq;

public interface ThreadDetailService {
	FindAllThreadDtlRes findAll(Long id) throws Exception;

	InsertRes insertById(InsertThreadDtlReq detail) throws Exception;
}
