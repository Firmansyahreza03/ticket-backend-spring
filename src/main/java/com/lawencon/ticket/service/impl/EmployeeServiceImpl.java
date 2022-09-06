package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.constant.RoleType;
import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.employee.EmployeeData;
import com.lawencon.ticket.dto.employee.FindAllEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByIdEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByUserIdEmployeeRes;
import com.lawencon.ticket.dto.employee.InsertEmployeeReq;
import com.lawencon.ticket.dto.employee.UpdatedEmployeeReq;
import com.lawencon.ticket.model.EmailDetails;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.EmailService;
import com.lawencon.ticket.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	private UserDao userDao;
	private RoleDao roleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FileDao fileDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public FindByIdEmployeeRes findById(Long id) throws Exception {

		Employee employee = employeeDao.findById(id);

		EmployeeData data = new EmployeeData();
		data.setId(employee.getId());
		data.setFullName(employee.getFullName());
		data.setUserId(employee.getUser().getId());
		data.setIsActive(employee.getIsActive());
		data.setVersion(employee.getVersion());
		data.setFileName(employee.getUser().getFile().getFileName());
		data.setFileExt(employee.getUser().getFile().getExtension());
		data.setFileId(employee.getUser().getFile().getId());
		data.setEmail(employee.getUser().getUserEmail());
		FindByIdEmployeeRes employeeRes = new FindByIdEmployeeRes();
		employeeRes.setData(data);
		return employeeRes;
	}

	@Override
	public FindAllEmployeeRes findAll() throws Exception {

		List<Employee> emps = employeeDao.findAll();
		List<EmployeeData> datas = new ArrayList<>();
		for (int i = 0; i < emps.size(); i++) {
			EmployeeData data = new EmployeeData();
			Employee employee = emps.get(i);
			data.setId(employee.getId());
			data.setFullName(employee.getFullName());
			data.setUserId(employee.getUser().getId());
			data.setIsActive(employee.getIsActive());
			data.setVersion(employee.getVersion());
			data.setFileId(employee.getUser().getFile().getId());
			data.setFileExt(employee.getUser().getFile().getExtension());
			data.setFileName(employee.getUser().getFile().getFileName());
			datas.add(data);
		}

		FindAllEmployeeRes empRes = new FindAllEmployeeRes();
		empRes.setDatas(datas);
		return empRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertEmployeeReq employeeReq) throws Exception {

		User user = new User();
		user.setUserEmail(employeeReq.getUser().getUserEmail());

		EmailDetails details = new EmailDetails();
		details.setRecipient(user.getUserEmail());
		String password = generatePassword();
		details.setMsgBody("Password anda adalah : " + password);
		details.setSubject("Password Ticket Management");
		new Thread(() -> emailService.sendSimpleMail(details)).start();

		user.setUserPass(passwordEncoder.encode(password));
		user.setIsActive(employeeReq.getIsActive());

		File file = new File();
		file.setFileName(employeeReq.getFileName());
		file.setExtension(employeeReq.getFileExt());
		file.setIsActive(employeeReq.getIsActive());
		file.setCreatedBy(getUserId());
		File fileRes = fileDao.insert(file);
		user.setFile(fileRes);
		Role role = roleDao.findByRoleCode(RoleType.PIC.getCode());
		user.setRole(role);
		user.setCreatedBy(getUserId());
		User userRes = userDao.insert(user);

		Employee empRes = new Employee();
		empRes.setFullName(employeeReq.getFullName());
		empRes.setUser(userRes);
		empRes.setCreatedBy(getUserId());
		empRes.setIsActive(employeeReq.getIsActive());
		Employee employee = employeeDao.insert(empRes);

		InsertResData data = new InsertResData();
		data.setId(employee.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");

		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdatedEmployeeReq employeeReq) throws Exception {
		Employee empRes = employeeDao.findById(employeeReq.getId());

		User user = userDao.findById(empRes.getUser().getId());

		File file = fileDao.findById(empRes.getUser().getFile().getId());
		if (employeeReq.getFileName() != null && employeeReq.getFileExt() != null) {
			file.setFileName(employeeReq.getFileName());
			file.setExtension(employeeReq.getFileExt());
			file.setIsActive(employeeReq.getIsActive());
		}
		file.setUpdatedBy(getUserId());
		file.setVersion(employeeReq.getVersion());
		File fileRes = fileDao.update(file);
		user.setFile(fileRes);

		Role role = roleDao.findByRoleCode(RoleType.PIC.getCode());
		user.setRole(role);
		user.setUpdatedBy(getUserId());
		user.setIsActive(employeeReq.getIsActive());
		user.setVersion(employeeReq.getVersion());
		User userRes = userDao.update(user);

		empRes.setFullName(employeeReq.getFullName());
		empRes.setUser(userRes);
		empRes.setUpdatedBy(getUserId());
		empRes.setIsActive(employeeReq.getIsActive());
		empRes.setVersion(employeeReq.getVersion());
		Employee employee = employeeDao.update(empRes);

		UpdateResData data = new UpdateResData();
		data.setVersion(employee.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Long idUser = employeeDao.findById(id).getUser().getId();
		Long idFile = employeeDao.findById(id).getUser().getFile().getId();
		Boolean empRes = employeeDao.deleteById(id);
		Boolean userRes = userDao.deleteById(idUser);
		Boolean fileRes = fileDao.deleteById(idFile);

		DeleteRes deleteRes = new DeleteRes();
		if (empRes && userRes && fileRes) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

	@Override
	public FindByUserIdEmployeeRes findByUserId() throws Exception {

		User user = userDao.findById(getUserId());
		Long idUser = user.getId();
		Employee empRes = employeeDao.findByUserId(idUser);

		EmployeeData data = new EmployeeData();
		data.setId(empRes.getId());
		data.setFullName(empRes.getFullName());
		data.setUserId(empRes.getUser().getId());
		data.setIsActive(empRes.getIsActive());
		data.setVersion(empRes.getVersion());
		data.setFileName(empRes.getUser().getFile().getFileName());
		data.setFileExt(empRes.getUser().getFile().getExtension());
		data.setEmail(empRes.getUser().getUserEmail());
		data.setFileId(empRes.getUser().getFile().getId());

		FindByUserIdEmployeeRes employeeRes = new FindByUserIdEmployeeRes();
		employeeRes.setData(data);
		return employeeRes;
	}

	private String generatePassword() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 8;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

}
