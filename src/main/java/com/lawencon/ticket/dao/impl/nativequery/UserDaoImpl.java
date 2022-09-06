package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.model.User;

@Profile("Native")
@Repository(value = "UserDaoImpl")
public class UserDaoImpl extends BaseEntityManager implements UserDao {

	@Override
	public User findById(Long id) throws Exception {
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public List<User> findAll() throws Exception {
		String sql = " SELECT id, user_email, role_id, is_active, \"version\" FROM t_user";

		List<User> users = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			User user = new User();
			user.setId(Long.valueOf(objArr[0].toString()));
			user.setUserEmail(objArr[1].toString());

			Role role = new Role();
			role.setId(Long.valueOf(objArr[2].toString()));
			user.setRole(role);

			user.setIsActive(Boolean.valueOf(objArr[3].toString()));
			user.setVersion(Integer.valueOf(objArr[4].toString()));
		});

		return users;
	}

	@Override
	public User insert(User user) throws Exception {
		em.persist(user);
		return user;
	}

	@Override
	public User update(User user) throws Exception {
		User userUpdate = em.merge(user);
		em.flush();
		return userUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM t_user WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		String sql = " SELECT tu.id, tu.user_email, tu.role_id, tr.role_name, tr.role_code, "
				+ " tu.is_active, tu.\"version\" " + " FROM t_user tu INNER JOIN t_role tr ON tr.id = tu.role_id "
				+ " WHERE user_email = :email ";
		User user = null;
		try {
			Object result = em.createNativeQuery(sql).setParameter("email", email).getSingleResult();

			if (result != null) {
				user = new User();
				Object[] objArr = (Object[]) result;
				user.setId(Long.valueOf(objArr[0].toString()));
				user.setUserEmail(objArr[1].toString());

				Role role = new Role();
				role.setId(Long.valueOf(objArr[2].toString()));
				role.setRoleName(objArr[3].toString());
				role.setRoleCode(objArr[4].toString());
				user.setRole(role);

				user.setIsActive(Boolean.valueOf(objArr[5].toString()));
				user.setVersion(Integer.valueOf(objArr[6].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
