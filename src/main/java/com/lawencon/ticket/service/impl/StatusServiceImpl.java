package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.StatusDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.status.FindAllStatusRes;
import com.lawencon.ticket.dto.status.FindByCodeStatusRes;
import com.lawencon.ticket.dto.status.FindByIdStatusRes;
import com.lawencon.ticket.dto.status.InsertStatusReq;
import com.lawencon.ticket.dto.status.StatusData;
import com.lawencon.ticket.dto.status.UpdateStatusReq;
import com.lawencon.ticket.model.Status;
import com.lawencon.ticket.service.StatusService;

@Service
public class StatusServiceImpl extends BaseServiceImpl implements StatusService {
	private StatusDao statusDao;

	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@Override
	public FindByIdStatusRes findById(Long id) throws Exception {
		Status status = statusDao.findById(id);

		StatusData data = new StatusData();
		data.setId(status.getId());
		data.setStatusName(status.getStatusName());
		data.setStatusCode(status.getStatusCode());
		data.setStatusPoint(status.getStatusPoint());
		data.setIsActive(status.getIsActive());
		data.setVersion(status.getVersion());

		FindByIdStatusRes statusRes = new FindByIdStatusRes();
		statusRes.setData(data);
		return statusRes;
	}

	@Override
	public FindByCodeStatusRes findByCode(String code) throws Exception {

		Status status = statusDao.findByCode(code);

		StatusData data = new StatusData();
		data.setId(status.getId());
		data.setStatusName(status.getStatusName());
		data.setStatusCode(status.getStatusCode());
		data.setStatusPoint(status.getStatusPoint());
		data.setIsActive(status.getIsActive());
		data.setVersion(status.getVersion());

		FindByCodeStatusRes statusRes = new FindByCodeStatusRes();
		statusRes.setData(data);
		return statusRes;
	}

	@Override
	public FindAllStatusRes findAll() throws Exception {

		List<Status> statusArr = statusDao.findAll();
		List<StatusData> datas = new ArrayList<>();
		for (int i = 0; i < statusArr.size(); i++) {
			StatusData data = new StatusData();
			Status status = statusArr.get(i);
			data.setId(status.getId());
			data.setStatusName(status.getStatusName());
			data.setStatusCode(status.getStatusCode());
			data.setStatusPoint(status.getStatusPoint());
			data.setIsActive(status.getIsActive());
			data.setVersion(status.getVersion());
			datas.add(data);
		}

		FindAllStatusRes statusRes = new FindAllStatusRes();
		statusRes.setDatas(datas);
		return statusRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertStatusReq status) throws Exception {

		Status statusRes = new Status();
		statusRes.setStatusName(status.getStatusName());
		statusRes.setStatusCode(status.getStatusCode());
		statusRes.setStatusPoint(status.getStatusPoint());
		statusRes.setIsActive(status.getIsActive());
		statusRes.setCreatedBy(getUserId());
		Status statusResult = statusDao.insert(statusRes);

		InsertResData data = new InsertResData();
		data.setId(statusResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateStatusReq status) throws Exception {

		Status statusResult = statusDao.findById(status.getId());
		statusResult.setStatusName(status.getStatusName());
		statusResult.setStatusPoint(status.getStatusPoint());
		statusResult.setUpdatedBy(getUserId());
		statusResult.setIsActive(status.getIsActive());
		statusResult.setVersion(status.getVersion());
		Status statusRes = statusDao.update(statusResult);

		UpdateResData data = new UpdateResData();
		data.setVersion(statusRes.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean statusResult = statusDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (statusResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

}
