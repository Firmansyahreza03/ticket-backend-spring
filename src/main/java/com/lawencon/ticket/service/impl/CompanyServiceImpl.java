package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.CompanyDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.company.CompanyData;
import com.lawencon.ticket.dto.company.FindAllCompanyRes;
import com.lawencon.ticket.dto.company.FindByIdCompanyRes;
import com.lawencon.ticket.dto.company.InsertCompanyReq;
import com.lawencon.ticket.dto.company.UpdateCompanyReq;
import com.lawencon.ticket.model.Company;
import com.lawencon.ticket.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService {
	private CompanyDao companyDao;

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public FindByIdCompanyRes findById(Long id) throws Exception {

		Company companyResult = companyDao.findById(id);

		CompanyData data = new CompanyData();
		data.setId(companyResult.getId());
		data.setCompanyName(companyResult.getCompanyName());
		data.setEmail(companyResult.getEmail());
		data.setAddress(companyResult.getAddress());
		data.setIsActive(companyResult.getIsActive());
		data.setVersion(companyResult.getVersion());

		FindByIdCompanyRes companyRes = new FindByIdCompanyRes();
		companyRes.setData(data);
		return companyRes;

	}

	@Override
	public FindAllCompanyRes findAll() throws Exception {

		List<Company> companyResult = companyDao.findAll();

		List<CompanyData> datas = new ArrayList<>();

		for (int i = 0; i < companyResult.size(); i++) {
			Company company = companyResult.get(i);
			CompanyData data = new CompanyData();
			data.setId(company.getId());
			data.setCompanyName(company.getCompanyName());
			data.setEmail(company.getEmail());
			data.setAddress(company.getAddress());
			data.setIsActive(company.getIsActive());
			data.setVersion(company.getVersion());
			datas.add(data);
		}

		FindAllCompanyRes companyRes = new FindAllCompanyRes();
		companyRes.setDatas(datas);
		return companyRes;

	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertCompanyReq company) throws Exception {

		Company companyRes = new Company();
		companyRes.setCompanyName(company.getCompanyName());
		companyRes.setEmail(company.getEmail());
		companyRes.setAddress(company.getAddress());
		companyRes.setCreatedBy(getUserId());
		companyRes.setIsActive(company.getIsActive());
		Company companyResult = companyDao.insert(companyRes);

		InsertResData data = new InsertResData();
		data.setId(companyResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateCompanyReq company) throws Exception {

		Company companyResult = companyDao.findById(company.getId());
		companyResult.setAddress(company.getAddress());
		companyResult.setUpdatedBy(getUserId());
		companyResult.setIsActive(company.getIsActive());
		companyResult.setVersion(company.getVersion());
		Company companyRes = companyDao.update(companyResult);

		UpdateResData data = new UpdateResData();
		data.setVersion(companyRes.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean companyRes = companyDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (companyRes) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}
}
