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
import com.lawencon.ticket.dto.user.ChangePassReq;
import com.lawencon.ticket.dto.user.ChangePassRes;
import com.lawencon.ticket.dto.user.FindAllUserRes;
import com.lawencon.ticket.dto.user.FindByIdUserRes;
import com.lawencon.ticket.dto.user.InsertUserReq;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<FindByIdUserRes> findById(@PathVariable("id") Long id) throws Exception{
		FindByIdUserRes userRes = userService.findById(id);
		return new ResponseEntity<FindByIdUserRes>(userRes, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<FindAllUserRes> findAll() throws Exception{
		FindAllUserRes userRes = userService.findAll();
		return new ResponseEntity<FindAllUserRes>(userRes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertUserReq user) throws Exception{
		InsertRes insertRes = userService.insert(user);
		return new ResponseEntity<InsertRes>(insertRes, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") Long id) throws Exception{
		DeleteRes deleteRes = userService.deleteById(id);
		return new ResponseEntity<DeleteRes>(deleteRes, HttpStatus.OK);
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<User> checkLogin(@PathVariable("email") String email) throws Exception{
		User userRes = userService.checkLogin(email);
		return new ResponseEntity<User>(userRes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ChangePassRes> changePass(@RequestBody @Valid ChangePassReq changePassReq) throws Exception{
		ChangePassRes changePassRes = userService.changePass(changePassReq);
		return new ResponseEntity<ChangePassRes>(changePassRes, HttpStatus.OK);
	}
}
