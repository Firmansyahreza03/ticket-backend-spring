package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.file.InsertFileReq;
import com.lawencon.ticket.dto.file.UpdateFileReq;
import com.lawencon.ticket.model.File;

public interface FileService {
	File findById(Long id) throws Exception;

	InsertRes insert(InsertFileReq fileReq) throws Exception;

	UpdateRes update(UpdateFileReq fileReq) throws Exception;

}
