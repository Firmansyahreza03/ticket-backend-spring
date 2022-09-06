package com.lawencon.ticket.dao.impl.nativequery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.File;

@Profile("Native")
@Repository(value = "FileDaoImpl")
public class FileDaoImpl extends BaseEntityManager implements FileDao {

	@Override
	public File findById(Long id) throws Exception {
		File file = em.find(File.class, id);
		return file;
	}

	@Override
	public File insert(File file) throws Exception {
		em.persist(file);
		return file;
	}

	@Override
	public File update(File file) throws Exception {
		File fileUpdate = em.merge(file);
		em.flush();
		return fileUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM t_file WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
	
	@Override
	public File findByFileName(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
