package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.EmployeeDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.User;

@Profile("Native")
@Repository(value = "EmployeeDaoImpl")
public class EmployeeDaoImpl extends BaseEntityManager implements EmployeeDao {
	@Override
	public Employee findById(Long id) throws Exception {
		Employee emp = em.find(Employee.class, id);
		return emp;
	}

	@Override
	public List<Employee> findAll() throws Exception {
		String sql = " SELECT * FROM t_employee te";

		List<Employee> emps = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Employee emp = new Employee();
			emp.setId(Long.valueOf(objArr[0].toString()));
			emp.setFullName(objArr[1].toString());

			User user = new User();
			user.setId(Long.valueOf(objArr[2].toString()));
			emp.setUser(user);

			emp.setIsActive(Boolean.valueOf(objArr[7].toString()));
			emp.setVersion(Integer.valueOf(objArr[8].toString()));

			emps.add(emp);
		});
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
		String sql = " DELETE FROM t_employee WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public Employee findByUserId(Long id) throws Exception {
		String sql = " SELECT * FROM t_employee WHERE user_id = :id ";

		Employee emp = null;
		try {
			Object result = em.createNativeQuery(sql).setParameter("id", id).getSingleResult();

			if (result != null) {
				emp = new Employee();
				Object[] objArr = (Object[]) result;
				emp.setId(Long.valueOf(objArr[0].toString()));
				emp.setFullName(objArr[1].toString());

				User user = new User();
				user.setId(Long.valueOf(objArr[2].toString()));
				emp.setUser(user);

				emp.setIsActive(Boolean.valueOf(objArr[7].toString()));
				emp.setVersion(Integer.valueOf(objArr[8].toString()));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}
}
