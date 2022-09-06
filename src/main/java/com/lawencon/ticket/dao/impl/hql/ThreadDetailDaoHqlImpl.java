package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadDetailDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.ThreadDetail;

@Profile("HQL")
@Repository(value = "ThreadDetailDaoHqlImpl")
public class ThreadDetailDaoHqlImpl extends BaseEntityManager implements ThreadDetailDao {

	@Override
	public List<ThreadDetail> findAll(Long id) throws Exception {
		String sql = " FROM ThreadDetail " + " WHERE header.id = :id ";

		List<ThreadDetail> details = new ArrayList<>();
		details = em.createQuery(sql, ThreadDetail.class).setParameter("id", id).getResultList();
		return details;
	}

	@Override
	public ThreadDetail insertById(ThreadDetail detail) throws Exception {
		em.persist(detail);
		return detail;
	}
}
