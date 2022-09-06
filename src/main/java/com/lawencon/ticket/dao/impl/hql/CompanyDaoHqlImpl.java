package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CompanyDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Company;

@Profile("HQL")
@Repository(value = "CompanyDaoHqlImpl")
public class CompanyDaoHqlImpl extends BaseEntityManager implements CompanyDao {

	@Override
	public Company findById(Long id) throws Exception {
		Company company = em.find(Company.class, id);
		return company;
	}

	@Override
	public List<Company> findAll() throws Exception {
		String sql = " FROM Company";

		List<Company> companies = new ArrayList<>();
		companies = em.createQuery(sql, Company.class).getResultList();
		return companies;
	}

	@Override
	public Company insert(Company company) throws Exception {
		em.persist(company);
		return company;
	}

	@Override
	public Company update(Company company) throws Exception {
		Company companyUpdate = em.merge(company);
		em.flush();
		return companyUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM Company WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
