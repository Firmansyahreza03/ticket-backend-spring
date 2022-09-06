package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadDetailDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.ThreadDetail;
import com.lawencon.ticket.model.ThreadHeader;

@Profile("Native")
@Repository(value = "ThreadDetailDaoImpl")
public class ThreadDetailDaoImpl extends BaseEntityManager implements ThreadDetailDao {

	@Override
	public List<ThreadDetail> findAll(Long id) throws Exception {
		String sql = " SELECT * FROM t_thread_dtl " + " WHERE hdr_id = :id ";

		List<ThreadDetail> details = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadDetail detail = new ThreadDetail();
			detail.setId(Long.valueOf(objArr[0].toString()));

			ThreadHeader threadHeader = new ThreadHeader();
			threadHeader.setId(Long.valueOf(objArr[1].toString()));
			detail.setHeader(threadHeader);

			detail.setCommentText(objArr[2].toString());
			
			File file = new File();
			file.setId(Long.valueOf(objArr[3].toString()));
			detail.setFile(file);
			detail.setIsActive(Boolean.valueOf(objArr[8].toString()));
			detail.setVersion(Integer.valueOf(objArr[9].toString()));

			details.add(detail);
		});
		return details;
	}

	@Override
	public ThreadDetail insertById(ThreadDetail detail) throws Exception {
		em.persist(detail);
		return detail;
	}
}
