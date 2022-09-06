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
import com.lawencon.ticket.dto.masterproduct.FindAllMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.FindByIdMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.InsertMasterProductReq;
import com.lawencon.ticket.dto.masterproduct.UpdateMasterProductReq;
import com.lawencon.ticket.service.MasterProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	private MasterProductService productService;
	
	@Autowired
	public void setProductService(MasterProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FindByIdMasterProductRes> findById(@PathVariable("id") Long id) throws Exception{
		FindByIdMasterProductRes productRes = productService.findById(id);
		return new ResponseEntity<FindByIdMasterProductRes>(productRes, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<FindAllMasterProductRes> findAll() throws Exception{
		FindAllMasterProductRes productRes = productService.findAll();
		return new ResponseEntity<FindAllMasterProductRes>(productRes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertMasterProductReq productReq) throws Exception{
		InsertRes insertRes = productService.insert(productReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdateMasterProductReq productReq) throws Exception{
		UpdateRes updateRes = productService.updateById(productReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception{
		DeleteRes deleteRes = productService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
}
