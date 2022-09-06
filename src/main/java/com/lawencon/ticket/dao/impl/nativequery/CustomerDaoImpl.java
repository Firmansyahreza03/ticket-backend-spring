package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Company;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.User;

@Profile("Native")
@Repository(value = "CustomerDaoImpl")
public class CustomerDaoImpl extends BaseEntityManager implements CustomerDao {
	@Override
	public Customer findById(Long id) throws Exception {
		Customer customer = em.find(Customer.class, id);
		return customer;
	}

	@Override
	public List<Customer> findAll() throws Exception {
		String sql = " SELECT * FROM t_customer";
		List<Customer> customers = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Customer customer = new Customer();
			customer.setId(Long.valueOf(objArr[0].toString()));
			customer.setFirstName(objArr[1].toString());
			customer.setLastName(objArr[2].toString());
			customer.setPhoneNumb(objArr[3].toString());
			customer.setAddress(objArr[4].toString());

			Company company = new Company();
			company.setId(Long.valueOf(objArr[5].toString()));
			customer.setCompany(company);

			User user = new User();
			user.setId(Long.valueOf(objArr[6].toString()));
			customer.setUser(user);

			customer.setIsActive(Boolean.valueOf(objArr[11].toString()));
			customer.setVersion(Integer.valueOf(objArr[12].toString()));
			customers.add(customer);
		});
		return customers;
	}

	@Override
	public Customer insert(Customer customer) throws Exception {
		em.persist(customer);
		return customer;
	}

	@Override
	public Customer update(Customer customer) throws Exception {
		Customer customerUpdate = em.merge(customer);
		em.flush();
		return customerUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM t_customer WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public Customer findByUserId(Long id) throws Exception {
		Customer customer = null;
		String sql = " SELECT * FROM t_customer WHERE user_id = :id ";
		try {
			Object result = em.createNativeQuery(sql).setParameter("id", id).getSingleResult();
			if (result != null) {
				customer = new Customer();
				Object[] objArr = (Object[]) result;
				customer.setId(Long.valueOf(objArr[0].toString()));
				customer.setFirstName(objArr[1].toString());
				customer.setLastName(objArr[2].toString());
				customer.setPhoneNumb(objArr[3].toString());
				customer.setAddress(objArr[4].toString());

				Company company = new Company();
				company.setId(Long.valueOf(objArr[5].toString()));
				customer.setCompany(company);

				User user = new User();
				user.setId(Long.valueOf(objArr[6].toString()));
				customer.setUser(user);

				customer.setIsActive(Boolean.valueOf(objArr[11].toString()));
				customer.setVersion(Integer.valueOf(objArr[12].toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
	}
}
