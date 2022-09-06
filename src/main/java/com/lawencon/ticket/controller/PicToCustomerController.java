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
import com.lawencon.ticket.dto.pictocustomer.FindAllCustPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindAllPtcRes;
import com.lawencon.ticket.dto.pictocustomer.FindByIdPtcRes;
import com.lawencon.ticket.dto.pictocustomer.InsertPtcReq;
import com.lawencon.ticket.dto.pictocustomer.UpdatePtcReq;
import com.lawencon.ticket.service.PicToCustomerService;

@RestController
@RequestMapping("pic-to-customers")
public class PicToCustomerController {

	private PicToCustomerService customerService;

	@Autowired
	public void setCustomerService(PicToCustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdPtcRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdPtcRes ptcRes = customerService.findById(id);
		return new ResponseEntity<FindByIdPtcRes>(ptcRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllPtcRes> findAll() throws Exception {
		FindAllPtcRes ptcRes = customerService.findAll();
		return new ResponseEntity<FindAllPtcRes>(ptcRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertPtcReq ptcReq) throws Exception {
		InsertRes insertRes = customerService.insert(ptcReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdatePtcReq ptcReq) throws Exception {
		UpdateRes updateRes = customerService.updateById(ptcReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@GetMapping("employees")
	public ResponseEntity<FindAllCustPtcRes> findAllCustomer() throws Exception {
		FindAllCustPtcRes ptcRes = customerService.findAllCustomer();
		return new ResponseEntity<FindAllCustPtcRes>(ptcRes, HttpStatus.OK);
	}

	@GetMapping("customers")
	public ResponseEntity<Long> findIdFromCustomer() throws Exception {
		Long ptcRes = customerService.findIdFromCustomer();
		return new ResponseEntity<Long>(ptcRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes res = customerService.deleteById(id);
		return new ResponseEntity<DeleteRes>(res, HttpStatus.OK);
	}
}
