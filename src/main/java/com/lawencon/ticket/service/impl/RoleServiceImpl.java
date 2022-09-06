package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.RoleDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.role.FindAllRoleRes;
import com.lawencon.ticket.dto.role.FindByIdRoleRes;
import com.lawencon.ticket.dto.role.InsertRoleReq;
import com.lawencon.ticket.dto.role.RoleData;
import com.lawencon.ticket.dto.role.UpdateRoleReq;
import com.lawencon.ticket.model.Role;
import com.lawencon.ticket.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	private RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public FindByIdRoleRes findById(Long id) throws Exception {

		Role role = roleDao.findById(id);

		RoleData data = new RoleData();
		data.setId(role.getId());
		data.setRoleName(role.getRoleName());
		data.setRoleCode(role.getRoleCode());
		data.setIsActive(role.getIsActive());
		data.setVersion(role.getVersion());

		FindByIdRoleRes roleRes = new FindByIdRoleRes();
		roleRes.setData(data);
		return roleRes;
	}

	@Override
	public FindAllRoleRes findAll() throws Exception {

		List<Role> roles = roleDao.findAll();
		List<RoleData> datas = new ArrayList<>();
		for (int i = 0; i < roles.size(); i++) {
			RoleData data = new RoleData();
			Role role = roles.get(i);
			data.setId(role.getId());
			data.setRoleName(role.getRoleName());
			data.setRoleCode(role.getRoleCode());
			data.setIsActive(role.getIsActive());
			data.setVersion(role.getVersion());
			datas.add(data);
		}

		FindAllRoleRes roleRes = new FindAllRoleRes();
		roleRes.setDatas(datas);
		return roleRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertRoleReq role) throws Exception {

		Role roleResult = new Role();
		roleResult.setRoleName(role.getRoleName());
		roleResult.setRoleCode(role.getRoleCode());
		roleResult.setIsActive(role.getIsActive());
		roleResult.setCreatedBy(getUserId());
		Role roleRes = roleDao.insert(roleResult);

		InsertResData data = new InsertResData();
		data.setId(roleRes.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateRoleReq role) throws Exception {

		Role roleResult = roleDao.findById(role.getId());
		roleResult.setRoleName(role.getRoleName());
		roleResult.setUpdatedBy(getUserId());
		roleResult.setIsActive(role.getIsActive());
		roleResult.setVersion(role.getVersion());
		Role roleRes = roleDao.update(roleResult);

		UpdateResData data = new UpdateResData();
		data.setVersion(roleRes.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		boolean roleResult = roleDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (roleResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}
}
