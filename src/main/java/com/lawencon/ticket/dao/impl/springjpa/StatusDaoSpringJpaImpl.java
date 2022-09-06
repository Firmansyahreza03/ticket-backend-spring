package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.StatusDao;
import com.lawencon.ticket.model.Status;
import com.lawencon.ticket.repo.StatusRepo;

@Profile(value = "springjpa")
@Repository
public class StatusDaoSpringJpaImpl implements StatusDao {

	private StatusRepo statusRepo;

	@Autowired
	public void setStatusRepo(StatusRepo statusRepo) {
		this.statusRepo = statusRepo;
	}

	@Override
	public Status findById(Long id) throws Exception {
		return statusRepo.findById(id).get();
	}

	@Override
	public Status findByCode(String code) throws Exception {
		Status res = statusRepo.findByStatusCode(code);
		return res;
	}

	@Override
	public List<Status> findAll() throws Exception {
		return statusRepo.findAll();
	}

	@Override
	public Status insert(Status status) throws Exception {
		return statusRepo.save(status);
	}

	@Override
	public Status update(Status status) throws Exception {
		return statusRepo.saveAndFlush(status);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = statusRepo.removeById(id);
		return res > 0;
	}
}
