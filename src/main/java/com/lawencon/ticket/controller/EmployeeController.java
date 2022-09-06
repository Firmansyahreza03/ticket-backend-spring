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
import com.lawencon.ticket.dto.employee.FindAllEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByIdEmployeeRes;
import com.lawencon.ticket.dto.employee.FindByUserIdEmployeeRes;
import com.lawencon.ticket.dto.employee.InsertEmployeeReq;
import com.lawencon.ticket.dto.employee.UpdatedEmployeeReq;
import com.lawencon.ticket.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdEmployeeRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdEmployeeRes employeeRes = employeeService.findById(id);
		return new ResponseEntity<FindByIdEmployeeRes>(employeeRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllEmployeeRes> findAll() throws Exception {
		FindAllEmployeeRes employeeRes = employeeService.findAll();
		return new ResponseEntity<FindAllEmployeeRes>(employeeRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertEmployeeReq employeeReq) throws Exception {
		InsertRes insertRes = employeeService.insert(employeeReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdatedEmployeeReq employeeReq) throws Exception {
		UpdateRes updateRes = employeeService.updateById(employeeReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes deleteRes = employeeService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<FindByUserIdEmployeeRes> findByUserId() throws Exception {
		FindByUserIdEmployeeRes employeeRes = employeeService.findByUserId();
		return new ResponseEntity<FindByUserIdEmployeeRes>(employeeRes, HttpStatus.OK);
	}
}
