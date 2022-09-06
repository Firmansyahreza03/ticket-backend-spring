package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.constant.RoleType;
import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.dao.ThreadDetailDao;
import com.lawencon.ticket.dao.ThreadHeaderDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.threaddtl.FindAllThreadDtlRes;
import com.lawencon.ticket.dto.threaddtl.InsertThreadDtlReq;
import com.lawencon.ticket.dto.threaddtl.ThreadDtlData;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.model.ThreadDetail;
import com.lawencon.ticket.model.ThreadHeader;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.ThreadDetailService;

@Service
public class ThreadDetailServiceImpl extends BaseServiceImpl implements ThreadDetailService {
	private ThreadDetailDao threadDetailDao;
	private ThreadHeaderDao headerDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private FileDao fileDao;

	@Autowired
	public void setHeaderDao(ThreadHeaderDao headerDao) {
		this.headerDao = headerDao;
	}

	@Autowired
	public void setThreadDetailDao(ThreadDetailDao threadDetailDao) {
		this.threadDetailDao = threadDetailDao;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insertById(InsertThreadDtlReq detail) throws Exception {

		ThreadDetail threadDetail = new ThreadDetail();

		ThreadHeader header = headerDao.findById(detail.getHeaderId());
		threadDetail.setHeader(header);

		threadDetail.setCommentText(detail.getCommentText());
		threadDetail.setIsActive(true);
		threadDetail.setCreatedBy(getUserId());

		File file = new File();
		if (detail.getFileName() != null) {
			file.setFileName(detail.getFileName());
			file.setExtension(detail.getFileExt());
			file.setCreatedBy(getUserId());
			file.setIsActive(true);
			File fileRes = fileDao.insert(file);
			threadDetail.setFile(fileRes);
		}

		ThreadDetail detailRes = threadDetailDao.insertById(threadDetail);

		InsertResData data = new InsertResData();
		data.setId(detailRes.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Insert success");
		return insertRes;
	}

	@Override
	public FindAllThreadDtlRes findAll(Long id) throws Exception {

		List<ThreadDetail> threadDetailRes = threadDetailDao.findAll(id);
		List<ThreadDtlData> datas = new ArrayList<>();
		for (int i = 0; i < threadDetailRes.size(); i++) {
			ThreadDetail detail = threadDetailRes.get(i);
			ThreadDtlData data = new ThreadDtlData();
			data.setId(detail.getId());
			data.setCommentText(detail.getCommentText());
			data.setHeaderId(detail.getHeader().getId());
			data.setIsActive(detail.getIsActive());
			data.setVersion(detail.getVersion());
			if (detail.getFile() != null) {
				data.setFileId(detail.getFile().getId());
				data.setFileName(detail.getFile().getFileName());
				data.setFileExt(detail.getFile().getExtension());
			}
			data.setCreatedAt(detail.getCreatedAt());

			User user = userDao.findById(detail.getCreatedBy());
			Role role = roleDao.findById(user.getRole().getId());
			if (RoleType.CUSTOMER.getCode().equals(role.getRoleCode())) {
				Customer customer = customerDao.findByUserId(detail.getCreatedBy());
				data.setPhotoId(customer.getUser().getFile().getId());
				data.setCreatorName(customer.getFirstName() + " " + customer.getLastName());
			} else if (RoleType.PIC.getCode().equals(role.getRoleCode())) {
				Employee employee = employeeDao
						.findByUserId(detail.getHeader().getPicToCustomer().getPic().getUser().getId());
				data.setPhotoId(employee.getUser().getFile().getId());
				data.setCreatorName(employee.getFullName());
			}
			data.setCreatedBy(detail.getCreatedBy());
			datas.add(data);
		}

		FindAllThreadDtlRes dtlRes = new FindAllThreadDtlRes();
		dtlRes.setDatas(datas);
		dtlRes.setId(getUserId());
		return dtlRes;
	}
}
