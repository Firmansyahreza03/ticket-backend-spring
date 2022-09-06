package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CompanyDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Company;

@Profile("Native")
@Repository(value = "CompanyDaoImpl")
public class CompanyDaoImpl extends BaseEntityManager implements CompanyDao {

	@Override
	public Company findById(Long id) throws Exception {
		Company company = em.find(Company.class, id);
		return company;
	}

	@Override
	public List<Company> findAll() throws Exception {
		String sql = " SELECT * FROM t_company";

		List<Company> companies = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Company company = new Company();
			company.setId(Long.valueOf(objArr[0].toString()));
			company.setCompanyName(objArr[1].toString());
			company.setEmail(objArr[2].toString());
			company.setAddress(objArr[3].toString());
			company.setIsActive(Boolean.valueOf(objArr[8].toString()));
			company.setVersion(Integer.valueOf(objArr[9].toString()));

			companies.add(company);
		});

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
		String sql = " DELETE FROM t_company WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
