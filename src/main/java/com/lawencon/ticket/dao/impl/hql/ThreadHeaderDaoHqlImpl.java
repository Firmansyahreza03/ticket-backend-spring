package com.lawencon.ticket.dao.impl.hql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadHeaderDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.ThreadHeader;

@Profile("HQL")
@Repository(value = "ThreadHeaderDaoHqlImpl")
public class ThreadHeaderDaoHqlImpl extends BaseEntityManager implements ThreadHeaderDao {

	@Override
	public ThreadHeader findById(Long id) throws Exception {
		ThreadHeader threadHeader = em.find(ThreadHeader.class, id);

		return threadHeader;
	}

	@Override
	public ThreadHeader insert(ThreadHeader threadHeader) throws Exception {
		em.persist(threadHeader);
		return threadHeader;
	}

	@Override
	public List<ThreadHeader> findAllByCustomerId(Long id) throws Exception {
		String sql = " FROM ThreadHeader tth " + " WHERE tth.picToCustomer.customer.id = :id "
				+ " ORDER BY tth.priority.priorityPoint DESC";

		List<ThreadHeader> threadHeaders = em.createQuery(sql, ThreadHeader.class).setParameter("id", id)
				.getResultList();
		return threadHeaders;
	}

	@Override
	public ThreadHeader updateStatus(ThreadHeader header) throws Exception {

		ThreadHeader threadHeaderUpdate = em.merge(header);
		em.flush();

		return threadHeaderUpdate;
	}
	
	@Override
	public String searchByCode(String code) throws Exception {
		String sql = " FROM ThreadHeader tth WHERE tth.code = :code ";
		ThreadHeader header = em.createQuery(sql, ThreadHeader.class).setParameter("code", code).getSingleResult();
		String headerCode = header.getCode();
		return headerCode;
	}
	
	@Override
	public List<ThreadHeader> findAllByPicId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ThreadHeader> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
