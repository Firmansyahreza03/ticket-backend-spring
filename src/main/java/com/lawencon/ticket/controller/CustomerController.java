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
import com.lawencon.ticket.dto.customer.FindAllCustomerRes;
import com.lawencon.ticket.dto.customer.FindByIdCustomerRes;
import com.lawencon.ticket.dto.customer.FindByUserIdCustomerRes;
import com.lawencon.ticket.dto.customer.InsertCustomerReq;
import com.lawencon.ticket.dto.customer.UpdateCustomerReq;
import com.lawencon.ticket.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdCustomerRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdCustomerRes customerRes = customerService.findById(id);
		return new ResponseEntity<FindByIdCustomerRes>(customerRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllCustomerRes> findAll() throws Exception {
		FindAllCustomerRes customerRes = customerService.findAll();
		return new ResponseEntity<FindAllCustomerRes>(customerRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertCustomerReq customerReq) throws Exception {
		InsertRes insertRes = customerService.insert(customerReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdateCustomerReq customerReq) throws Exception {
		UpdateRes updateRes = customerService.updateById(customerReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes deleteRes = customerService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<FindByUserIdCustomerRes> findByUserId() throws Exception {
		FindByUserIdCustomerRes customerRes = customerService.findByUserId();
		return new ResponseEntity<FindByUserIdCustomerRes>(customerRes, HttpStatus.OK);
	}
}
