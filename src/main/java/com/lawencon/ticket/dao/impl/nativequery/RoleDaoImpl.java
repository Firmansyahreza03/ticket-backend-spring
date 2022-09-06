package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Role;

@Profile("Native")
@Repository(value = "RoleDaoImpl")
public class RoleDaoImpl extends BaseEntityManager implements RoleDao {

	@Override
	public Role findById(Long id) throws Exception {
		Role role = em.find(Role.class, id);
		return role;
	}

	@Override
	public List<Role> findAll() throws Exception {
		String sql = " SELECT * FROM t_role";

		List<Role> roles = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Role role = new Role();
			role.setId(Long.valueOf(objArr[0].toString()));
			role.setRoleName(objArr[1].toString());
			role.setRoleCode(objArr[2].toString());
			role.setIsActive(Boolean.valueOf(objArr[7].toString()));
			role.setVersion(Integer.valueOf(objArr[8].toString()));

			roles.add(role);
		});
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
		String sql = " DELETE FROM t_role WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
	
	@Override
	public Role findByRoleCode(String roleCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
