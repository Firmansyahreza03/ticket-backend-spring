package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PriorityDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Priority;

@Profile("HQL")
@Repository(value = "PriorityDaoHqlImpl")
public class PriorityDaoHqlImpl extends BaseEntityManager implements PriorityDao {

	@Override
	public Priority findById(Long id) throws Exception {
		Priority priority = em.find(Priority.class, id);
		return priority;
	}

	@Override
	public List<Priority> findAll() throws Exception {
		String sql = " FROM Priority";

		List<Priority> priorities = new ArrayList<>();
		priorities = em.createQuery(sql, Priority.class).getResultList();
		return priorities;
	}

	@Override
	public Priority insert(Priority priority) throws Exception {
		em.persist(priority);
		return priority;
	}

	@Override
	public Priority update(Priority priority) throws Exception {
		Priority priorityUpdate = em.merge(priority);
		em.flush();
		return priorityUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM Priority WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
