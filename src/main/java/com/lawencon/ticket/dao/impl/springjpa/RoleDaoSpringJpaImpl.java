package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.repo.RoleRepo;

@Profile(value = "springjpa")
@Repository
public class RoleDaoSpringJpaImpl implements RoleDao {

	private RoleRepo roleRepo;

	@Autowired
	public void setRoleRepo(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findById(Long id) throws Exception {
		return roleRepo.findById(id).get();
	}

	@Override
	public List<Role> findAll() throws Exception {
		return roleRepo.findAll();
	}

	@Override
	public Role insert(Role role) throws Exception {
		return roleRepo.save(role);
	}

	@Override
	public Role update(Role role) throws Exception {
		return roleRepo.saveAndFlush(role);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int result = roleRepo.removeById(id);
		return result > 0;
	}
	
	@Override
	public Role findByRoleCode(String roleCode) throws Exception {
		Role role = roleRepo.findByRoleCode(roleCode);
		return role;
	}
}
