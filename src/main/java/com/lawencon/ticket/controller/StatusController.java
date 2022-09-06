package com.lawencon.ticket.controller;

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
import com.lawencon.ticket.dto.status.FindAllStatusRes;
import com.lawencon.ticket.dto.status.FindByIdStatusRes;
import com.lawencon.ticket.dto.status.InsertStatusReq;
import com.lawencon.ticket.dto.status.UpdateStatusReq;
import com.lawencon.ticket.service.StatusService;

@RestController
@RequestMapping("status")
public class StatusController {
	private StatusService statusService;

	@Autowired
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdStatusRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdStatusRes statusRes = statusService.findById(id);
		return new ResponseEntity<FindByIdStatusRes>(statusRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllStatusRes> findAll() throws Exception {
		FindAllStatusRes statusRes = statusService.findAll();
		return new ResponseEntity<FindAllStatusRes>(statusRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody InsertStatusReq statusReq) throws Exception {
		InsertRes insertRes = statusService.insert(statusReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody UpdateStatusReq statusReq) throws Exception {
		UpdateRes updateRes = statusService.updateById(statusReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> deleteById(@PathVariable Long id) throws Exception {
		DeleteRes deleteRes = statusService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
}
