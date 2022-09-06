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
import com.lawencon.ticket.dto.customerproduct.FindAllByCustCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindAllCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindByIdCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.InsertCustomerProductReq;
import com.lawencon.ticket.dto.customerproduct.UpdatedByIdCustomerProductReq;
import com.lawencon.ticket.service.CustomerProductService;

@RestController
@RequestMapping("customer-products")
public class CustProductController {

	private CustomerProductService productService;

	@Autowired
	public void setProductService(CustomerProductService productService) {
		this.productService = productService;
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdCustomerProductRes> findById(@PathVariable("id") Long id) throws Exception {
		FindByIdCustomerProductRes productRes = productService.findById(id);
		return new ResponseEntity<FindByIdCustomerProductRes>(productRes, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<FindAllCustomerProductRes> findAll() throws Exception {
		FindAllCustomerProductRes productRes = productService.findAll();
		return new ResponseEntity<FindAllCustomerProductRes>(productRes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertCustomerProductReq productReq) throws Exception {
		InsertRes insertRes = productService.insert(productReq);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid UpdatedByIdCustomerProductReq productReq)
			throws Exception {
		UpdateRes updateRes = productService.updateById(productReq);
		return new ResponseEntity<UpdateRes>(updateRes, HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<FindAllByCustCustomerProductRes> findAllByCustomerId()
			throws Exception {
		FindAllByCustCustomerProductRes productRes = productService.findAllProductByCust();
		return new ResponseEntity<FindAllByCustCustomerProductRes>(productRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception {
		DeleteRes res = productService.deleteById(id);
		return new ResponseEntity<DeleteRes>(res, HttpStatus.OK);
	}
}
