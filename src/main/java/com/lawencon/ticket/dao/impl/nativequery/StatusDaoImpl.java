package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.StatusDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Status;

@Profile("Native")
@Repository(value = "StatusDaoImpl")
public class StatusDaoImpl extends BaseEntityManager implements StatusDao {

	@Override
	public Status findById(Long id) throws Exception {
		Status status = null;
		String sql = " SELECT * FROM t_status WHERE id = :id ";
		Object result = em.createNativeQuery(sql).setParameter("id", id).getSingleResult();
		if(result != null) {
			status = new Status();
			status = new Status();
			Object[] objArr = (Object[]) result;
			status.setId(Long.valueOf(objArr[0].toString()));
			status.setStatusName(objArr[1].toString());
			status.setStatusCode(objArr[2].toString());
			status.setStatusPoint(Long.valueOf(objArr[3].toString()));
			status.setIsActive(Boolean.valueOf(objArr[8].toString()));
			status.setVersion(Integer.valueOf(objArr[9].toString()));
		}
		return status;
	}
	
	@Override
	public Status findByCode(String code) throws Exception {
		Status status = null;
		String sql = " SELECT * FROM t_status WHERE status_code = :code ";
		try {
			Object result = em.createNativeQuery(sql)
					.setParameter("code", code)
					.getSingleResult();
			
			if(result != null) {
				status = new Status();
				Object[] objArr = (Object[]) result;
				status.setId(Long.valueOf(objArr[0].toString()));
				status.setStatusName(objArr[1].toString());
				status.setStatusCode(objArr[2].toString());
				status.setStatusPoint(Long.valueOf(objArr[3].toString()));
				status.setIsActive(Boolean.valueOf(objArr[8].toString()));
				status.setVersion(Integer.valueOf(objArr[9].toString()));
			}
		}catch (Exception e) {
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
		String sql = " SELECT * FROM t_status";
		
		List<Status> statusArr = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj ->{
			Object[] objArr = (Object[]) obj;
			Status status = new Status();
			status.setId(Long.valueOf(objArr[0].toString()));
			status.setStatusName(objArr[1].toString());
			status.setStatusCode(objArr[2].toString());
			status.setStatusPoint(Long.valueOf(objArr[3].toString()));
			status.setIsActive(Boolean.valueOf(objArr[8].toString()));
			status.setVersion(Integer.valueOf(objArr[9].toString()));
			
			statusArr.add(status);
		});
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
		String sql = " DELETE FROM t_status WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
