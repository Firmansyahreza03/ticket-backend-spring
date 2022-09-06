package com.lawencon.ticket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.threadhdr.FindAllThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.FindByIdThreadHdrRes;
import com.lawencon.ticket.dto.threadhdr.InsertThreadHdrReq;
import com.lawencon.ticket.dto.threadhdr.UpdateStatusThreadHdrReq;
import com.lawencon.ticket.service.ThreadHeaderService;

@RestController
@RequestMapping("tickets")
public class TicketController {

	private ThreadHeaderService headerService;

	@Autowired
	public void setHeaderService(ThreadHeaderService headerService) {
		this.headerService = headerService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdThreadHdrRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdThreadHdrRes hdrRes = headerService.findById(id);
		return new ResponseEntity<FindByIdThreadHdrRes>(hdrRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertThreadHdrReq hdrReq) throws Exception {
		InsertRes insertRes = headerService.insert(hdrReq);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}

	@GetMapping("customers")
	public ResponseEntity<FindAllThreadHdrRes> findAllByCustomerId() throws Exception {
		FindAllThreadHdrRes hdrRes = headerService.findAllByCustomerId();
		return new ResponseEntity<FindAllThreadHdrRes>(hdrRes, HttpStatus.OK);
	}

	@GetMapping("pic")
	public ResponseEntity<FindAllThreadHdrRes> findAllByPicId() throws Exception {
		FindAllThreadHdrRes res = headerService.findAllByPicId();
		return new ResponseEntity<FindAllThreadHdrRes>(res, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> updateStatus(@RequestBody @Valid UpdateStatusThreadHdrReq hdrReq)
			throws Exception {
		UpdateRes updateRes = headerService.updateStatus(hdrReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllThreadHdrRes> findAll() throws Exception {
		FindAllThreadHdrRes res = headerService.findAll();
		return new ResponseEntity<FindAllThreadHdrRes>(res, HttpStatus.OK);
	}
}
