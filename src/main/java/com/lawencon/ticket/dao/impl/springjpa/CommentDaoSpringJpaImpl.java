package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadDetailDao;
import com.lawencon.ticket.model.ThreadDetail;
import com.lawencon.ticket.repo.ThreadDetailRepo;

@Profile(value = "springjpa")
@Repository
public class CommentDaoSpringJpaImpl implements ThreadDetailDao {

	private ThreadDetailRepo threadDetailRepo;

	@Autowired
	public void setThreadDetailRepo(ThreadDetailRepo threadDetailRepo) {
		this.threadDetailRepo = threadDetailRepo;
	}

	@Override
	public ThreadDetail insertById(ThreadDetail detail) throws Exception {
		return threadDetailRepo.save(detail);
	}
	
	@Override
	public List<ThreadDetail> findAll(Long id) throws Exception {
		List<ThreadDetail> details = threadDetailRepo.findByHeaderId(id);
		return details;
	}
}
