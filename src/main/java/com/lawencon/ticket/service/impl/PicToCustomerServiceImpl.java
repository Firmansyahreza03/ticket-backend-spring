package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.dao.PicToCustomerDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.pictocustomer.FindAllCustPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindAllPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindByIdPtcRes;
import com.lawencon.ticket.dto.pictocustomer.InsertPtcReq;
import com.lawencon.ticket.dto.pictocustomer.PicToCustomerData;
import com.lawencon.ticket.dto.pictocustomer.UpdatePtcReq;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.PicToCustomer;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.PicToCustomerService;

@Service
public class PicToCustomerServiceImpl extends BaseServiceImpl implements PicToCustomerService {
	private PicToCustomerDao picToCustomerDao;
	private CustomerDao customerDao;
	private EmployeeDao employeeDao;
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Autowired
	public void setPicToCustomerDao(PicToCustomerDao picToCustomerDao) {
		this.picToCustomerDao = picToCustomerDao;
	}

	@Override
	public FindByIdPtcRes findById(Long id) throws Exception {

		PicToCustomer ptcRes = picToCustomerDao.findById(id);

		PicToCustomerData data = new PicToCustomerData();
		data.setId(ptcRes.getId());
		data.setPicId(ptcRes.getPic().getId());
		data.setPicName(ptcRes.getPic().getFullName());
		data.setCustomerId(ptcRes.getCustomer().getId());
		data.setCustomerName(ptcRes.getCustomer().getFirstName() + ptcRes.getCustomer().getLastName());
		data.setIsActive(ptcRes.getIsActive());
		data.setVersion(ptcRes.getVersion());

		FindByIdPtcRes res = new FindByIdPtcRes();
		res.setData(data);
		return res;
	}

	@Override
	public FindAllPtcRes findAll() throws Exception {

		List<PicToCustomer> ptcRes = picToCustomerDao.findAll();
		List<PicToCustomerData> datas = new ArrayList<>();
		for (int i = 0; i < ptcRes.size(); i++) {
			PicToCustomerData data = new PicToCustomerData();
			PicToCustomer picToCustomer = ptcRes.get(i);
			data.setId(picToCustomer.getId());
			data.setPicId(picToCustomer.getPic().getId());
			data.setPicName(picToCustomer.getPic().getFullName());
			data.setCustomerId(picToCustomer.getCustomer().getId());
			data.setCustomerName(
					picToCustomer.getCustomer().getFirstName() + " " + picToCustomer.getCustomer().getLastName());
			data.setIsActive(picToCustomer.getIsActive());
			data.setVersion(picToCustomer.getVersion());
			datas.add(data);
		}

		FindAllPtcRes res = new FindAllPtcRes();
		res.setDatas(datas);

		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertPtcReq ptcReq) throws Exception {

		PicToCustomer ptcRes = new PicToCustomer();

		Employee pic = employeeDao.findById(ptcReq.getPicId());
		ptcRes.setPic(pic);

		Customer customer = customerDao.findById(ptcReq.getCustomerId());
		ptcRes.setCustomer(customer);

		ptcRes.setIsActive(ptcReq.getIsActive());
		ptcRes.setCreatedBy(getUserId());
		PicToCustomer res = picToCustomerDao.insert(ptcRes);

		InsertResData data = new InsertResData();
		data.setId(res.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdatePtcReq ptcReq) throws Exception {

		PicToCustomer ptc = picToCustomerDao.findById(ptcReq.getId());
		Employee employee = employeeDao.findById(ptcReq.getPicId());
		ptc.setPic(employee);
		ptc.setUpdatedBy(getUserId());
		ptc.setVersion(ptcReq.getVersion());
		ptc.setIsActive(ptcReq.getIsActive());

		PicToCustomer ptcRes = picToCustomerDao.update(ptc);

		UpdateResData data = new UpdateResData();
		data.setVersion(ptcRes.getVersion());

		UpdateRes res = new UpdateRes();
		res.setData(data);
		res.setMessage("Berhasil mengubah data");
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean empRes = picToCustomerDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (empRes) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

	@Override
	public FindAllCustPtcRes findAllCustomer() throws Exception {

		User employee = userDao.findById(getUserId());
		List<PicToCustomer> ptcRes = picToCustomerDao.findAllCustomer(employee.getId());
		List<PicToCustomerData> datas = new ArrayList<>();
		for (int i = 0; i < ptcRes.size(); i++) {
			PicToCustomerData data = new PicToCustomerData();
			PicToCustomer customer = ptcRes.get(i);
			data.setId(customer.getId());
			data.setPicId(customer.getPic().getId());
			data.setPicName(customer.getPic().getFullName());
			data.setCustomerId(customer.getCustomer().getId());
			data.setCustomerName(customer.getCustomer().getFirstName() + customer.getCustomer().getLastName());
			data.setIsActive(customer.getIsActive());
			data.setVersion(customer.getVersion());
			datas.add(data);
		}

		FindAllCustPtcRes custPtcRes = new FindAllCustPtcRes();
		custPtcRes.setDatas(datas);
		return custPtcRes;
	}

	@Override
	public Long findIdFromCustomer() throws Exception {

		Long ptcRes = picToCustomerDao.findIdFromCustomer(getUserId());
		return ptcRes;
	}
}
