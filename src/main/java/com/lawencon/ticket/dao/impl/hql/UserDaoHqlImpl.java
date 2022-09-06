package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.User;

@Profile("HQL")
@Repository(value = "UserDaoHqlImpl")
public class UserDaoHqlImpl extends BaseEntityManager implements UserDao {

	@Override
	public User findById(Long id) throws Exception {
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public List<User> findAll() throws Exception {
		String sql = " FROM User";

		List<User> users = new ArrayList<>();
		users = em.createQuery(sql, User.class).getResultList();

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
		String sql = " DELETE FROM User WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		String sql = " FROM User as tu " + " WHERE tu.userEmail = :email";
		User user = null;
		try {
			user = em.createQuery(sql, User.class).setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
