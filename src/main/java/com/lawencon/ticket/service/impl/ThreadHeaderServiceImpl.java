package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.constant.StatusType;
import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.dao.CustomerProductDao;
import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.dao.PicToCustomerDao;
import com.lawencon.ticket.dao.PriorityDao;
import com.lawencon.ticket.dao.StatusDao;
import com.lawencon.ticket.dao.ThreadHeaderDao;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.threadhdr.FindAllThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.FindByIdThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.InsertThreadHdrReq;
import com.lawencon.ticket.dto.threadhdr.ThreadHdrData;
import com.lawencon.ticket.dto.threadhdr.UpdateStatusThreadHdrReq;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.CustomerProduct;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.PicToCustomer;
import com.lawencon.ticket.model.Priority;
import com.lawencon.ticket.model.Status;
import com.lawencon.ticket.model.ThreadHeader;
import com.lawencon.ticket.service.ThreadHeaderService;

@Service
public class ThreadHeaderServiceImpl extends BaseServiceImpl implements ThreadHeaderService {
	private ThreadHeaderDao threadHeaderDao;
	private StatusDao statusDao;
	private PicToCustomerDao ptcDao;
	private PriorityDao priorityDao;
	private CustomerProductDao productDao;
	private FileDao fileDao;
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Autowired
	public void setProductDao(CustomerProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	@Autowired
	public void setPtcDao(PicToCustomerDao ptcDao) {
		this.ptcDao = ptcDao;
	}

	@Autowired
	public void setThreadHeaderDao(ThreadHeaderDao threadHeaderDao) {
		this.threadHeaderDao = threadHeaderDao;
	}

	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@Override
	public FindByIdThreadHdrRes findById(Long id) throws Exception {
		ThreadHeader header = threadHeaderDao.findById(id);

		ThreadHdrData data = new ThreadHdrData();
		data.setId(header.getId());
		data.setCode(header.getCode());
		data.setDescription(header.getDescription());
		if(header.getFile() != null) {			
			data.setFileId(header.getFile().getId());
			data.setFileName(header.getFile().getFileName());
			data.setFileExt(header.getFile().getExtension());
		}
		data.setCustomerId(header.getPicToCustomer().getCustomer().getId());
		data.setCustomerName(header.getPicToCustomer().getCustomer().getFirstName() + " "
				+ header.getPicToCustomer().getCustomer().getLastName());
		data.setPicId(header.getPicToCustomer().getPic().getId());
		data.setPicName(header.getPicToCustomer().getPic().getFullName());
		data.setPriorityId(header.getPriority().getId());
		data.setPriorityName(header.getPriority().getPriorityName());
		data.setProductId(header.getProductCustomer().getProduct().getId());
		data.setProductName(header.getProductCustomer().getProduct().getProductName());
		data.setStatusId(header.getStatus().getId());
		data.setStatusName(header.getStatus().getStatusName());
		data.setTitle(header.getTitle());
		data.setIsActive(header.getIsActive());
		data.setVersion(header.getVersion());
		data.setCreatedAt(header.getCreatedAt());
		
		FindByIdThreadHdrRes hdrRes = new FindByIdThreadHdrRes();
		hdrRes.setData(data);
		return hdrRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertThreadHdrReq threadHeader) throws Exception {

		ThreadHeader header = new ThreadHeader();
		header.setDescription(threadHeader.getDescription());

		String code = generateCode();
		String codeDb = threadHeaderDao.searchByCode(code);
		if (codeDb == null) {
			header.setCode(generateCode());
		}
		header.setTitle(threadHeader.getTitle());
		Customer customer = customerDao.findByUserId(getUserId());
		PicToCustomer ptc = ptcDao.findByCustomerId(customer.getId());
		header.setPicToCustomer(ptc);
		
		Status status = statusDao.findByCode(StatusType.OPN.name());
		header.setStatus(status);

		Priority priority = priorityDao.findById(threadHeader.getPriorityId());
		header.setPriority(priority);

		CustomerProduct product = productDao.findByProductId(threadHeader.getProductId());
		header.setProductCustomer(product);

		File file = new File();
		if(threadHeader.getFileName()!=null) {			
			file.setFileName(threadHeader.getFileName());
			file.setExtension(threadHeader.getFileExt());
			file.setIsActive(true);
			file.setCreatedBy(getUserId());
			File fileRes = fileDao.insert(file);
			header.setFile(fileRes);
		}

		header.setIsActive(true);
		header.setCreatedBy(getUserId());
		ThreadHeader headerRes = threadHeaderDao.insert(header);

		InsertResData data = new InsertResData();
		data.setId(headerRes.getId());
		
		ThreadHdrData dataRes = new ThreadHdrData();
		dataRes.setId(headerRes.getId());
		dataRes.setCode(headerRes.getCode());
		dataRes.setTitle(headerRes.getTitle());
		dataRes.setPriorityName(headerRes.getPriority().getPriorityName());
		dataRes.setStatusName(headerRes.getStatus().getStatusName());

		FindByIdThreadHdrRes res = new FindByIdThreadHdrRes();
		res.setData(dataRes);
		
		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Insert success");
		return insertRes;
	}

	@Override
	public FindAllThreadHdrRes findAll() throws Exception {
		List<ThreadHeader> threadHeaderRes = threadHeaderDao.findAll();
		List<ThreadHdrData> datas = new ArrayList<>();
		for (int i = 0; i < threadHeaderRes.size(); i++) {
			ThreadHeader header = threadHeaderRes.get(i);
			ThreadHdrData data = new ThreadHdrData();
			data.setCode(header.getCode());
			data.setDescription(header.getDescription());
			if(header.getFile() != null) {				
				data.setFileId(header.getFile().getId());
				data.setFileName(header.getFile().getFileName());
				data.setFileExt(header.getFile().getExtension());
			}
			data.setId(header.getId());
			data.setCustomerId(header.getPicToCustomer().getCustomer().getId());
			data.setCustomerName(header.getPicToCustomer().getCustomer().getFirstName() + " "
					+ header.getPicToCustomer().getCustomer().getLastName());
			data.setPicId(header.getPicToCustomer().getPic().getId());
			data.setPicName(header.getPicToCustomer().getPic().getFullName());
			data.setPriorityId(header.getPriority().getId());
			data.setPriorityName(header.getPriority().getPriorityName());
			data.setProductId(header.getProductCustomer().getProduct().getId());
			data.setProductName(header.getProductCustomer().getProduct().getProductName());
			data.setStatusName(header.getStatus().getStatusName());
			data.setTitle(header.getTitle());
			data.setIsActive(header.getIsActive());
			data.setVersion(header.getVersion());
			datas.add(data);
		}

		FindAllThreadHdrRes hdrRes = new FindAllThreadHdrRes();
		hdrRes.setDatas(datas);
		return hdrRes;
	}

	@Override
	public FindAllThreadHdrRes findAllByCustomerId() throws Exception {

		List<ThreadHeader> threadHeaderRes = threadHeaderDao.findAllByCustomerId(getUserId());
		List<ThreadHdrData> datas = new ArrayList<>();
		for (int i = 0; i < threadHeaderRes.size(); i++) {
			ThreadHeader header = threadHeaderRes.get(i);
			ThreadHdrData data = new ThreadHdrData();
			data.setCode(header.getCode());
			data.setDescription(header.getDescription());
			if(header.getFile() != null) {				
				data.setFileId(header.getFile().getId());
				data.setFileName(header.getFile().getFileName());
				data.setFileExt(header.getFile().getExtension());
			}
			data.setId(header.getId());
			data.setCustomerId(header.getPicToCustomer().getCustomer().getId());
			data.setCustomerName(header.getPicToCustomer().getCustomer().getFirstName() + " "
					+ header.getPicToCustomer().getCustomer().getLastName());
			data.setPicId(header.getPicToCustomer().getPic().getId());
			data.setPicName(header.getPicToCustomer().getPic().getFullName());
			data.setPriorityId(header.getPriority().getId());
			data.setPriorityName(header.getPriority().getPriorityName());
			data.setProductId(header.getProductCustomer().getProduct().getId());
			data.setProductName(header.getProductCustomer().getProduct().getProductName());
			data.setStatusName(header.getStatus().getStatusName());
			data.setTitle(header.getTitle());
			data.setIsActive(header.getIsActive());
			data.setVersion(header.getVersion());
			datas.add(data);
		}

		FindAllThreadHdrRes hdrRes = new FindAllThreadHdrRes();
		hdrRes.setDatas(datas);
		return hdrRes;
	}

	@Override
	public FindAllThreadHdrRes findAllByPicId() throws Exception {
		List<ThreadHeader> threadHeaderRes = threadHeaderDao.findAllByPicId(getUserId());
		List<ThreadHdrData> datas = new ArrayList<>();
		for (int i = 0; i < threadHeaderRes.size(); i++) {
			ThreadHeader header = threadHeaderRes.get(i);
			ThreadHdrData data = new ThreadHdrData();
			data.setCode(header.getCode());
			data.setDescription(header.getDescription());
			if(header.getFile() != null) {							
				data.setFileId(header.getFile().getId());
				data.setFileName(header.getFile().getFileName());
				data.setFileExt(header.getFile().getExtension());
			}
			data.setId(header.getId());
			data.setCustomerId(header.getPicToCustomer().getCustomer().getId());
			data.setCustomerName(header.getPicToCustomer().getCustomer().getFirstName() + " "
					+ header.getPicToCustomer().getCustomer().getLastName());
			data.setPicId(header.getPicToCustomer().getPic().getId());
			data.setPicName(header.getPicToCustomer().getPic().getFullName());
			data.setPriorityId(header.getPriority().getId());
			data.setPriorityName(header.getPriority().getPriorityName());
			data.setProductId(header.getProductCustomer().getProduct().getId());
			data.setProductName(header.getProductCustomer().getProduct().getProductName());
			data.setStatusName(header.getStatus().getStatusName());
			data.setTitle(header.getTitle());
			data.setIsActive(header.getIsActive());
			data.setVersion(header.getVersion());
			datas.add(data);
		}

		FindAllThreadHdrRes res = new FindAllThreadHdrRes();
		res.setDatas(datas);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateStatus(UpdateStatusThreadHdrReq header) throws Exception {

		ThreadHeader headerRes = threadHeaderDao.findById(header.getId());

		Status status = headerRes.getStatus();
		if (StatusType.OPN.name().equals(status.getStatusCode())
				|| StatusType.ROPN.name().equals(status.getStatusCode())) {

			Status newStatus = statusDao.findByCode(StatusType.CLS.name());
			headerRes.setStatus(newStatus);
		} else {
			Status newStatus = statusDao.findByCode(StatusType.ROPN.name());
			headerRes.setStatus(newStatus);
		}

		if (StatusType.CLS.name().equals(status.getStatusCode())) {
			headerRes.setIsActive(false);
		} else {
			headerRes.setIsActive(true);
		}

		headerRes.setUpdatedBy(getUserId());
		ThreadHeader res = threadHeaderDao.updateStatus(headerRes);

		UpdateResData data = new UpdateResData();
		data.setVersion(res.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Edit sukses");
		return updateRes;
	}

	private String generateCode() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + numbers;
		Random random = new Random();
		char[] password = new char[5];
		String passwordRes = "T-";
		for (int i = 0; i < 5; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
			passwordRes = passwordRes + password[i];
		}
		return passwordRes;
	}
}
