package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CompanyDao;
import com.lawencon.ticket.model.Company;
import com.lawencon.ticket.repo.CompanyRepo;

@Profile(value = "springjpa")
@Repository
public class CompanyDaoSpringJpaImpl implements CompanyDao {

	private CompanyRepo companyRepo;

	@Autowired
	public void setCompanyRepo(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

	@Override
	public Company findById(Long id) throws Exception {
		return companyRepo.findById(id).get();
	}

	@Override
	public List<Company> findAll() throws Exception {
		return companyRepo.findAll();
	}

	@Override
	public Company insert(Company company) throws Exception {
		return companyRepo.save(company);
	}

	@Override
	public Company update(Company company) throws Exception {
		return companyRepo.saveAndFlush(company);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int result = companyRepo.removeById(id);
		return result > 0;
	}
}
