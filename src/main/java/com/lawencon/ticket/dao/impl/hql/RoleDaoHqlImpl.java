package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Role;

@Profile("HQL")
@Repository(value = "RoleDaoHqlImpl")
public class RoleDaoHqlImpl extends BaseEntityManager implements RoleDao {

	@Override
	public Role findById(Long id) throws Exception {
		Role role = em.find(Role.class, id);
		return role;
	}

	@Override
	public List<Role> findAll() throws Exception {
		String sql = " FROM Role";

		List<Role> roles = new ArrayList<>();
		roles = em.createQuery(sql, Role.class).getResultList();
		return roles;
	}

	@Override
	public Role insert(Role role) throws Exception {
		em.persist(role);
		return role;
	}

	@Override
	public Role update(Role role) throws Exception {
		Role roleUpdate = em.merge(role);
		em.flush();
		return roleUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM Role WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
	
	@Override
	public Role findByRoleCode(String roleCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}