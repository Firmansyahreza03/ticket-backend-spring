package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.constant.RoleType;
import com.lawencon.ticket.dao.CompanyDao;
import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.customer.CustomerData;
import com.lawencon.ticket.dto.customer.FindAllCustomerRes;
import com.lawencon.ticket.dto.customer.FindByIdCustomerRes;
import com.lawencon.ticket.dto.customer.FindByUserIdCustomerRes;
import com.lawencon.ticket.dto.customer.InsertCustomerReq;
import com.lawencon.ticket.dto.customer.UpdateCustomerReq;
import com.lawencon.ticket.model.Company;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.EmailDetails;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.CustomerService;
import com.lawencon.ticket.service.EmailService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {
	private CustomerDao customerDao;
	private UserDao userDao;
	private CompanyDao companyDao;
	private RoleDao roleDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FileDao fileDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public FindByIdCustomerRes findById(Long id) throws Exception {

		Customer customer = customerDao.findById(id);

		CustomerData data = new CustomerData();
		data.setId(customer.getId());
		data.setFirstName(customer.getFirstName());
		data.setLastName(customer.getLastName());
		data.setAddress(customer.getAddress());
		data.setPhoneNumb(customer.getPhoneNumb());
		data.setCompanyId(customer.getCompany().getId());
		data.setCompanyName(customer.getCompany().getCompanyName());
		data.setUserId(customer.getUser().getId());
		data.setIsActive(customer.getIsActive());
		data.setVersion(customer.getVersion());
		data.setFileName(customer.getUser().getFile().getFileName());
		data.setFileExt(customer.getUser().getFile().getExtension());
		data.setFileId(customer.getUser().getFile().getId());
		data.setEmail(customer.getUser().getUserEmail());
		FindByIdCustomerRes customerRes = new FindByIdCustomerRes();
		customerRes.setData(data);
		return customerRes;

	}

	@Override
	public FindAllCustomerRes findAll() throws Exception {

		List<Customer> customers = customerDao.findAll();
		List<CustomerData> datas = new ArrayList<>();
		for (int i = 0; i < customers.size(); i++) {
			CustomerData data = new CustomerData();
			Customer customer = customers.get(i);
			data.setId(customer.getId());
			data.setFirstName(customer.getFirstName());
			data.setLastName(customer.getLastName());
			data.setPhoneNumb(customer.getPhoneNumb());
			data.setAddress(customer.getAddress());
			data.setCompanyId(customer.getCompany().getId());
			data.setCompanyName(customer.getCompany().getCompanyName());
			data.setUserId(customer.getUser().getId());
			data.setIsActive(customer.getIsActive());
			data.setVersion(customer.getVersion());
			data.setFileId(customer.getUser().getFile().getId());
			data.setFileExt(customer.getUser().getFile().getExtension());
			data.setFileName(customer.getUser().getFile().getFileName());
			datas.add(data);
		}

		FindAllCustomerRes customerRes = new FindAllCustomerRes();
		customerRes.setDatas(datas);
		return customerRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertCustomerReq customerReq) throws Exception {

		Company company = companyDao.findById(customerReq.getCompanyId());

		User user = new User();
		user.setUserEmail(customerReq.getUser().getUserEmail());

		String password = generatePassword();
		EmailDetails details = new EmailDetails();
		details.setRecipient(user.getUserEmail());
		details.setMsgBody("Password anda adalah : " + password);
		details.setSubject("Password Ticket Management");
		new Thread(() -> emailService.sendSimpleMail(details)).start();

		user.setUserPass(passwordEncoder.encode(password));
		user.setIsActive(customerReq.getIsActive());
		user.setCreatedBy(getUserId());

		File file = new File();
		file.setFileName(customerReq.getFileName());
		file.setExtension(customerReq.getFileExt());
		file.setCreatedBy(getUserId());
		file.setIsActive(customerReq.getIsActive());
		File fileRes = fileDao.insert(file);
		user.setFile(fileRes);
		Role role = roleDao.findByRoleCode(RoleType.CUSTOMER.getCode());
		user.setRole(role);
		User userRes = userDao.insert(user);

		Customer customer = new Customer();
		customer.setFirstName(customerReq.getFirstName());
		customer.setLastName(customerReq.getLastName());
		customer.setAddress(customerReq.getAddress());
		customer.setCompany(company);
		customer.setIsActive(customerReq.getIsActive());
		customer.setPhoneNumb(customerReq.getPhoneNumb());
		customer.setUser(userRes);
		customer.setCreatedBy(getUserId());
		Customer customerResult = customerDao.insert(customer);

		InsertResData data = new InsertResData();
		data.setId(customerResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateCustomerReq customerReq) throws Exception {

		Customer customer = customerDao.findById(customerReq.getId());

		Company company = companyDao.findById(customerReq.getCompanyId());

		User user = userDao.findById(customer.getUser().getId());

		File file = fileDao.findById(customer.getUser().getFile().getId());
		if (customerReq.getFileName() != null && customerReq.getFileExt() != null) {
			file.setFileName(customerReq.getFileName());
			file.setExtension(customerReq.getFileExt());
			file.setIsActive(customerReq.getIsActive());
		}
		file.setVersion(customerReq.getVersion());
		file.setUpdatedBy(getUserId());
		File fileRes = fileDao.update(file);
		user.setFile(fileRes);

		Role role = roleDao.findByRoleCode(RoleType.CUSTOMER.getCode());
		user.setRole(role);
		user.setUpdatedBy(getUserId());
		user.setIsActive(customerReq.getIsActive());
		user.setVersion(customerReq.getVersion());
		User userRes = userDao.update(user);

		customer.setFirstName(customerReq.getFirstName());
		customer.setLastName(customerReq.getLastName());
		customer.setAddress(customerReq.getAddress());
		customer.setCompany(company);
		customer.setUser(userRes);
		customer.setUpdatedBy(getUserId());
		customer.setIsActive(customerReq.getIsActive());
		customer.setVersion(customerReq.getVersion());
		Customer customerResult = customerDao.update(customer);

		UpdateResData data = new UpdateResData();
		data.setVersion(customerResult.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Long idCust = customerDao.findById(id).getUser().getId();
		Long idFile = customerDao.findById(id).getUser().getFile().getId();
		Boolean customerResult = customerDao.deleteById(id);
		Boolean userResult = userDao.deleteById(idCust);
		Boolean fileResult = fileDao.deleteById(idFile);
		DeleteRes deleteRes = new DeleteRes();
		if (customerResult && userResult && fileResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

	@Override
	public FindByUserIdCustomerRes findByUserId() throws Exception {

		User user = userDao.findById(getUserId());
		Long idUser = user.getId();
		Customer custRes = customerDao.findByUserId(idUser);

		CustomerData data = new CustomerData();
		data.setId(custRes.getId());
		data.setFirstName(custRes.getFirstName());
		data.setLastName(custRes.getLastName());
		data.setAddress(custRes.getAddress());
		data.setCompanyName(custRes.getCompany().getCompanyName());
		data.setPhoneNumb(custRes.getPhoneNumb());
		data.setUserId(custRes.getUser().getId());
		data.setIsActive(custRes.getIsActive());
		data.setVersion(custRes.getVersion());
		data.setFileExt(custRes.getUser().getFile().getExtension());
		data.setFileId(custRes.getUser().getFile().getId());
		data.setFileName(custRes.getUser().getFile().getFileName());
		data.setEmail(custRes.getUser().getUserEmail());

		FindByUserIdCustomerRes customerRes = new FindByUserIdCustomerRes();
		customerRes.setData(data);
		return customerRes;
	}

	private String generatePassword() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

}