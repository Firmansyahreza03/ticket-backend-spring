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
import com.lawencon.ticket.dto.company.FindAllCompanyRes;
import com.lawencon.ticket.dto.company.FindByIdCompanyRes;
import com.lawencon.ticket.dto.company.InsertCompanyReq;
import com.lawencon.ticket.dto.company.UpdateCompanyReq;
import com.lawencon.ticket.service.CompanyService;

@RestController
@RequestMapping("companies")
public class CompanyController {

	private CompanyService companyService;

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdCompanyRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdCompanyRes company = companyService.findById(id);
		return new ResponseEntity<FindByIdCompanyRes>(company, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllCompanyRes> findAll() throws Exception {
		FindAllCompanyRes companies = companyService.findAll();
		return new ResponseEntity<FindAllCompanyRes>(companies, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertCompanyReq company) throws Exception {
		InsertRes insertRes = companyService.insert(company);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdateCompanyReq company) throws Exception {
		UpdateRes companyRes = companyService.updateById(company);
		return new ResponseEntity<UpdateRes>(companyRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes deleteRes = companyService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
}
