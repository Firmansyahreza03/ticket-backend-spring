package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PriorityDao;
import com.lawencon.ticket.model.Priority;
import com.lawencon.ticket.repo.PriorityRepo;

@Profile(value = "springjpa")
@Repository
public class PrioritySpringJpaImpl implements PriorityDao {

	private PriorityRepo priorityRepo;

	@Autowired
	public void setPriorityRepo(PriorityRepo priorityRepo) {
		this.priorityRepo = priorityRepo;
	}

	@Override
	public Priority findById(Long id) throws Exception {
		return priorityRepo.findById(id).get();
	}

	@Override
	public List<Priority> findAll() throws Exception {
		return priorityRepo.findAll();
	}

	@Override
	public Priority insert(Priority priority) throws Exception {
		return priorityRepo.save(priority);
	}

	@Override
	public Priority update(Priority priority) throws Exception {
		return priorityRepo.saveAndFlush(priority);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = priorityRepo.removeById(id);
		return res > 0;
	}
}
