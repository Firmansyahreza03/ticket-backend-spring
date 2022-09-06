package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Customer;

@Profile("HQL")
@Repository(value = "CustomerDaoHqlImpl")
public class CustomerDaoHqlImpl extends BaseEntityManager implements CustomerDao {
	@Override
	public Customer findById(Long id) throws Exception {
		Customer customer = em.find(Customer.class, id);
		return customer;
	}

	@Override
	public List<Customer> findAll() throws Exception {
		String sql = " FROM Customer";
		List<Customer> customers = new ArrayList<>();
		customers = em.createQuery(sql, Customer.class).getResultList();
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
		String sql = " DELETE FROM Customer WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public Customer findByUserId(Long id) throws Exception {
		Customer customer = null;
		String sql = " FROM Customer WHERE user.id = :id ";
		try {
			customer = (Customer) em.createQuery(sql, Customer.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
	}
}
