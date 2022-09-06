package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.StatusDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Status;

@Profile("HQL")
@Repository(value = "StatusDaoHqlImpl")
public class StatusDaoHqlImpl extends BaseEntityManager implements StatusDao {

	@Override
	public Status findById(Long id) throws Exception {
		Status status = em.find(Status.class, id);
		return status;
	}
	
	@Override
	public Status findByCode(String code) throws Exception {
		Status status = null;
		String sql = "FROM Status WHERE statusCode = :code ";
		try {
			status = em.createQuery(sql, Status.class).setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Status insert(Status status) throws Exception {
		em.persist(status);
		return status;
	}

	@Override
	public List<Status> findAll() throws Exception {
		String sql = "FROM Status ";

		List<Status> statusArr = new ArrayList<>();
		statusArr = em.createQuery(sql, Status.class).getResultList();
		return statusArr;
	}

	@Override
	public Status update(Status status) throws Exception {
		Status statusUpdate = em.merge(status);
		em.flush();
		return statusUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM Status WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
