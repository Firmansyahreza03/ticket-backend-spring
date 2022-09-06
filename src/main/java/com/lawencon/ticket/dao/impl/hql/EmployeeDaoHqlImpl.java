package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Employee;

@Profile("HQL")
@Repository(value = "EmployeeDaoHqlImpl")
public class EmployeeDaoHqlImpl extends BaseEntityManager implements EmployeeDao {
	@Override
	public Employee findById(Long id) throws Exception {
		Employee emp = em.find(Employee.class, id);
		return emp;
	}

	@Override
	public List<Employee> findAll() throws Exception {
		String sql = " FROM Employee te";

		List<Employee> emps = new ArrayList<>();
		emps = em.createQuery(sql, Employee.class).getResultList();
		return emps;
	}

	@Override
	public Employee insert(Employee employee) throws Exception {
		em.persist(employee);
		return employee;
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		Employee employeeUpdate = em.merge(employee);
		em.flush();
		return employeeUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM Employee WHERE id = :id";
		int res = em.createQuery(sql, Employee.class).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public Employee findByUserId(Long id) throws Exception {
		String sql = " FROM Employee WHERE user.id = :id ";

		Employee emp = null;
		try {
			emp = (Employee) em.createQuery(sql).setParameter("id", id).getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}
}
