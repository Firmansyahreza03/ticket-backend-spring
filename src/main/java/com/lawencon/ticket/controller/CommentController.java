package com.lawencon.ticket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.threaddtl.FindAllThreadDtlRes;
import com.lawencon.ticket.dto.threaddtl.InsertThreadDtlReq;
import com.lawencon.ticket.service.ThreadDetailService;

@RestController
@RequestMapping("comments")
public class CommentController {

	private ThreadDetailService detailService;

	@Autowired
	public void setDetailService(ThreadDetailService detailService) {
		this.detailService = detailService;
	}

	@GetMapping("tickets/{id}")
	public ResponseEntity<FindAllThreadDtlRes> findAll(@PathVariable("id") Long id) throws Exception {
		FindAllThreadDtlRes dtlRes = detailService.findAll(id);
		return new ResponseEntity<FindAllThreadDtlRes>(dtlRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertThreadDtlReq dtlReq) throws Exception {
		InsertRes insertRes = detailService.insertById(dtlReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}
}
