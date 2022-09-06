package com.lawencon.ticket.dao.impl.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.FileDao;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.repo.FileRepo;

@Profile(value = "springjpa")
@Repository
public class FileDaoSpringJpaImpl implements FileDao {

	private FileRepo fileRepo;

	@Autowired
	public void setFileRepo(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
	}

	@Override
	public File findById(Long id) throws Exception {
		return fileRepo.findById(id).get();
	}

	@Override
	public File insert(File file) throws Exception {
		return fileRepo.save(file);
	}

	@Override
	public File update(File file) throws Exception {
		return fileRepo.saveAndFlush(file);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = fileRepo.removeById(id);
		return res > 0;
	}
	
	@Override
	public File findByFileName(String fileName) throws Exception {
		File res = fileRepo.findByFileName(fileName);
		return res;
	}
}
