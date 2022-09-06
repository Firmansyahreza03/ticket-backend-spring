package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.user.ChangePassReq;
import com.lawencon.ticket.dto.user.ChangePassRes;
import com.lawencon.ticket.dto.user.FindAllUserRes;
import com.lawencon.ticket.dto.user.FindByIdUserRes;
import com.lawencon.ticket.dto.user.InsertUserReq;
import com.lawencon.ticket.dto.user.UpdateUserReq;
import com.lawencon.ticket.dto.user.UserData;
import com.lawencon.ticket.model.EmailDetails;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.EmailService;
import com.lawencon.ticket.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;

	@Override
	public FindByIdUserRes findById(Long id) throws Exception {

		User user = userDao.findById(id);

		UserData data = new UserData();
		data.setUserEmail(user.getUserEmail());
		data.setRoleCode(user.getRole().getRoleCode());
		data.setIsActive(user.getIsActive());
		data.setVersion(user.getVersion());

		FindByIdUserRes userRes = new FindByIdUserRes();
		userRes.setData(data);
		return userRes;
	}

	@Override
	public FindAllUserRes findAll() throws Exception {

		List<User> users = userDao.findAll();
		List<UserData> datas = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			UserData data = new UserData();
			User user = users.get(i);
			data.setUserEmail(user.getUserEmail());
			data.setRoleCode(user.getRole().getRoleCode());
			data.setIsActive(user.getIsActive());
			data.setVersion(user.getVersion());
			datas.add(data);
		}

		FindAllUserRes userRes = new FindAllUserRes();
		userRes.setDatas(datas);
		return userRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertUserReq user) throws Exception {

		User userRes = new User();
		userRes.setUserEmail(user.getUserEmail());
		String password = generatePassword();
		userRes.setUserPass(passwordEncoder.encode(password));
		userRes.setIsActive(true);

		EmailDetails details = new EmailDetails();
		details.setRecipient(user.getUserEmail());
		details.setMsgBody("Password anda adalah : " + password);
		details.setSubject("Password Ticket Management");
		new Thread(() -> emailService.sendSimpleMail(details)).start();

		Role role = roleDao.findById(user.getRoleId());
		userRes.setRole(role);

		List<User> users = userDao.findAll();
		if (users.size() != 0) {
			userRes.setCreatedBy(getUserId());
		}
		User userResult = userDao.insert(userRes);

		InsertResData data = new InsertResData();
		data.setId(userResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");

		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateUserReq user) throws Exception {

		User userRes = userDao.findById(user.getId());
		userRes.setUserPass(user.getUserPass());

		Role role = roleDao.findById(user.getRoleId());
		userRes.setRole(role);
		userRes.setUpdatedBy(getUserId());
		userRes.setIsActive(user.getIsActive());
		userRes.setVersion(user.getVersion());
		User userResult = userDao.update(userRes);

		UpdateResData data = new UpdateResData();
		data.setVersion(userResult.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean userResult = userDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (userResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

	@Override
	public User checkLogin(String email) throws Exception {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userDb = new User();
		try {
			userDb = userDao.findByEmail(email);
			if (userDb == null) {
				throw new RuntimeException("User Invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new org.springframework.security.core.userdetails.User(email, userDb.getUserPass(), new ArrayList<>());
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

	@Override
	public ChangePassRes changePass(ChangePassReq passReq) throws Exception {
		User user = userDao.findById(getUserId());

		String oldPassEncoded = passReq.getOldPass();
		String currentPass = user.getUserPass();
		ChangePassRes changePassRes = new ChangePassRes();

		if (passwordEncoder.matches(oldPassEncoded, currentPass)) {
			String newPassEncoded = passwordEncoder.encode(passReq.getNewPass());
			user.setUserPass(newPassEncoded);
			userDao.update(user);
			changePassRes.setMessage("Ubah password berhasil");
		} else {
			changePassRes.setMessage("Ubah password gagal");
		}
		return changePassRes;

	}

}
