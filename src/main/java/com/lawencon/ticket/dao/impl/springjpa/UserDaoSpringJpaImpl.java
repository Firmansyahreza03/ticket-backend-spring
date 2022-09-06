package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.repo.UserRepo;

@Profile(value = "springjpa")
@Repository
public class UserDaoSpringJpaImpl implements UserDao {

	private UserRepo userRepo;

	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User findById(Long id) throws Exception {
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> findAll() throws Exception {
		return userRepo.findAll();
	}

	@Override
	public User findByEmail(String email) throws Exception {
		User res = userRepo.findByUserEmail(email);
		return res;
	}

	@Override
	public User insert(User user) throws Exception {
		return userRepo.save(user);
	}

	@Override
	public User update(User user) throws Exception {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = userRepo.removeById(id);
		return res > 0;
	}
}
