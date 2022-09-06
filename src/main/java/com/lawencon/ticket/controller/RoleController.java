package com.lawencon.ticket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.role.FindAllRoleRes;
import com.lawencon.ticket.dto.role.FindByIdRoleRes;
import com.lawencon.ticket.dto.role.InsertRoleReq;
import com.lawencon.ticket.dto.role.UpdateRoleReq;
import com.lawencon.ticket.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {

	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdRoleRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdRoleRes roleRes = roleService.findById(id);
		return new ResponseEntity<FindByIdRoleRes>(roleRes, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<FindAllRoleRes> findAll() throws Exception{
		FindAllRoleRes roleRes = roleService.findAll();
		return new ResponseEntity<FindAllRoleRes>(roleRes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertRoleReq roleReq) throws Exception{
		InsertRes insertRes = roleService.insert(roleReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdateRoleReq roleReq) throws Exception{
		UpdateRes updateRes = roleService.updateById(roleReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception{
		DeleteRes deleteRes = roleService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
}
