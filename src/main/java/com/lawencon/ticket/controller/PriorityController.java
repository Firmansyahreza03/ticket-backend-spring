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
import com.lawencon.ticket.dto.priority.FindAllPriorityRes;
import com.lawencon.ticket.dto.priority.FindByIdPriorityRes;
import com.lawencon.ticket.dto.priority.InsertPriorityReq;
import com.lawencon.ticket.dto.priority.UpdatePriorityReq;
import com.lawencon.ticket.service.PriorityService;

@RestController
@RequestMapping("priorities")
public class PriorityController {
	private PriorityService priorityService;

	@Autowired
	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdPriorityRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdPriorityRes priorityRes = priorityService.findById(id);
		return new ResponseEntity<FindByIdPriorityRes>(priorityRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllPriorityRes> findAll() throws Exception {
		FindAllPriorityRes priorityRes = priorityService.findAll();
		return new ResponseEntity<FindAllPriorityRes>(priorityRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertPriorityReq priorityReq) throws Exception {
		InsertRes insertRes = priorityService.insert(priorityReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdatePriorityReq priorityReq) throws Exception {
		UpdateRes updateRes = priorityService.updateById(priorityReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes deleteRes = priorityService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
}
