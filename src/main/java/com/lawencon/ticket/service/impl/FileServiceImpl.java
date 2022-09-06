package com.lawencon.ticket.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.file.FileData;
import com.lawencon.ticket.dto.file.FindByIdFileRes;
import com.lawencon.ticket.dto.file.InsertFileReq;
import com.lawencon.ticket.dto.file.UpdateFileReq;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.service.FileService;

@Service
public class FileServiceImpl extends BaseServiceImpl implements FileService {
	@Autowired
	private FileDao fileDao;

	@Override
	public File findById(Long id) throws Exception {
		File fileResult = fileDao.findById(id);
		FileData data = new FileData();
		data.setId(fileResult.getId());
		data.setExtension(fileResult.getExtension());
		data.setFileName(fileResult.getFileName());
		data.setIsActive(fileResult.getIsActive());
		data.setVersion(fileResult.getVersion());

		FindByIdFileRes res = new FindByIdFileRes();
		res.setData(data);
		return fileResult;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertFileReq fileReq) throws Exception {
		File file = new File();
		file.setCreatedBy(getUserId());
		file.setExtension(fileReq.getExtension());
		file.setFileName(fileReq.getFileName());
		file.setIsActive(fileReq.getIsActive());
		File fileRes = fileDao.insert(file);

		InsertResData data = new InsertResData();
		data.setId(fileRes.getId());

		InsertRes res = new InsertRes();
		res.setData(data);
		res.setMessage("Insert success");
		return res;
	}

	@Transactional(rollbackOn = Exception.class )
	@Override
	public UpdateRes update(UpdateFileReq fileReq) throws Exception {
		File fileRes = fileDao.findById(fileReq.getId());
		fileRes.setFileName(fileReq.getFileName());
		fileRes.setExtension(fileReq.getExtension());
		fileRes.setUpdatedBy(getUserId());
		fileRes.setVersion(fileReq.getVersion());
		
		UpdateResData data = new UpdateResData();
		data.setVersion(fileRes.getVersion());
		
		UpdateRes res = new UpdateRes();
		res.setData(data);
		res.setMessage("Update success");
		return res;
	}
}
