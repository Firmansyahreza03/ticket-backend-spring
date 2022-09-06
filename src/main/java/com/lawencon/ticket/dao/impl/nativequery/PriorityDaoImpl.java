package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PriorityDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Priority;

@Profile("Native")
@Repository(value = "PriorityDaoImpl")
public class PriorityDaoImpl extends BaseEntityManager implements PriorityDao {

	@Override
	public Priority findById(Long id) throws Exception {
		Priority priority = em.find(Priority.class, id);
		return priority;
	}

	@Override
	public List<Priority> findAll() throws Exception {
		String sql = " SELECT * FROM t_priority";

		List<Priority> priorities = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Priority priority = new Priority();
			priority.setId(Long.valueOf(objArr[0].toString()));
			priority.setPriorityName(objArr[1].toString());
			priority.setPriorityCode(objArr[2].toString());
			priority.setIsActive(Boolean.valueOf(objArr[9].toString()));
			priority.setVersion(Integer.valueOf(objArr[10].toString()));

			priorities.add(priority);
		});
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
		String sql = " DELETE FROM t_priority WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
