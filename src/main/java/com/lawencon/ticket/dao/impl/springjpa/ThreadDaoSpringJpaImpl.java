package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadHeaderDao;
import com.lawencon.ticket.model.ThreadHeader;
import com.lawencon.ticket.repo.ThreadHeaderRepo;

@Profile(value = "springjpa")
@Repository
public class ThreadDaoSpringJpaImpl implements ThreadHeaderDao {

	private ThreadHeaderRepo headerRepo;

	@Autowired
	public void setHeaderRepo(ThreadHeaderRepo headerRepo) {
		this.headerRepo = headerRepo;
	}

	@Override
	public ThreadHeader findById(Long id) throws Exception {
		return headerRepo.findById(id).get();
	}

	@Override
	public List<ThreadHeader> findAllByCustomerId(Long id) throws Exception {
		List<ThreadHeader> res = headerRepo.findByPicToCustomerCustomerUserIdOrderByPriorityPriorityPointDesc(id);
		return res;
	}

	@Override
	public ThreadHeader insert(ThreadHeader threadHeader) throws Exception {
		return headerRepo.save(threadHeader);
	}

	@Override
	public ThreadHeader updateStatus(ThreadHeader header) throws Exception {
		return headerRepo.saveAndFlush(header);
	}

	@Override
	public String searchByCode(String code) throws Exception {
		String res = headerRepo.findCodeByCode(code);
		return res;
	}

	@Override
	public List<ThreadHeader> findAllByPicId(Long id) throws Exception {
		List<ThreadHeader> res = headerRepo.findByPicToCustomerPicUserIdOrderByPriorityPriorityPointDesc(id);
		return res;
	}

	@Override
	public List<ThreadHeader> findAll() throws Exception {
		return headerRepo.findAll();
	}
}
