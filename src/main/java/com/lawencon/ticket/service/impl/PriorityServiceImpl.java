package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.PriorityDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.priority.FindAllPriorityRes;
import com.lawencon.ticket.dto.priority.FindByIdPriorityRes;
import com.lawencon.ticket.dto.priority.InsertPriorityReq;
import com.lawencon.ticket.dto.priority.PriorityData;
import com.lawencon.ticket.dto.priority.UpdatePriorityReq;
import com.lawencon.ticket.model.Priority;
import com.lawencon.ticket.service.PriorityService;

@Service
public class PriorityServiceImpl extends BaseServiceImpl implements PriorityService {
	private PriorityDao priorityDao;

	@Autowired
	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	@Override
	public FindByIdPriorityRes findById(Long id) throws Exception {

		Priority priority = priorityDao.findById(id);

		PriorityData data = new PriorityData();
		data.setId(priority.getId());
		data.setPriorityName(priority.getPriorityName());
		data.setPriorityCode(priority.getPriorityCode());
		data.setPriorityPoint(priority.getPriorityPoint());
		data.setIsActive(priority.getIsActive());
		data.setVersion(priority.getVersion());

		FindByIdPriorityRes priorityRes = new FindByIdPriorityRes();
		priorityRes.setData(data);
		return priorityRes;
	}

	@Override
	public FindAllPriorityRes findAll() throws Exception {

		List<Priority> priorities = priorityDao.findAll();
		List<PriorityData> datas = new ArrayList<>();
		for (int i = 0; i < priorities.size(); i++) {
			PriorityData data = new PriorityData();
			Priority priority = priorities.get(i);
			data.setId(priority.getId());
			data.setPriorityName(priority.getPriorityName());
			data.setPriorityCode(priority.getPriorityCode());
			data.setPriorityPoint(priority.getPriorityPoint());
			data.setIsActive(priority.getIsActive());
			data.setVersion(priority.getVersion());
			datas.add(data);
		}

		FindAllPriorityRes priorityRes = new FindAllPriorityRes();
		priorityRes.setDatas(datas);
		return priorityRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertPriorityReq priority) throws Exception {

		Priority priorityRes = new Priority();
		priorityRes.setPriorityName(priority.getPriorityName());
		priorityRes.setPriorityCode(priority.getPriorityCode());
		priorityRes.setPriorityPoint(priority.getPriorityPoint());
		priorityRes.setIsActive(priority.getIsActive());
		priorityRes.setCreatedBy(getUserId());
		Priority priorityResult = priorityDao.insert(priorityRes);

		InsertResData data = new InsertResData();
		data.setId(priorityResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdatePriorityReq priority) throws Exception {

		Priority priorityResult = priorityDao.findById(priority.getId());
		priorityResult.setPriorityName(priority.getPriorityName());
		priorityResult.setPriorityPoint(priority.getPriorityPoint());
		priorityResult.setUpdatedBy(getUserId());
		priorityResult.setIsActive(priority.getIsActive());
		priorityResult.setVersion(priority.getVersion());
		Priority priorityRes = priorityDao.update(priorityResult);

		UpdateResData data = new UpdateResData();
		data.setVersion(priorityRes.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean priorityResult = priorityDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (priorityResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}
}
