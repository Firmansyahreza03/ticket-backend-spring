package com.lawencon.ticket.service;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.role.FindAllRoleRes;
import com.lawencon.ticket.dto.role.FindByIdRoleRes;
import com.lawencon.ticket.dto.role.InsertRoleReq;
import com.lawencon.ticket.dto.role.UpdateRoleReq;

public interface RoleService {
	FindByIdRoleRes findById(Long id) throws Exception;

	FindAllRoleRes findAll() throws Exception;

	InsertRes insert(InsertRoleReq role) throws Exception;

	UpdateRes updateById(UpdateRoleReq role) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;
}
