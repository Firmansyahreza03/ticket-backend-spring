package com.lawencon.ticket.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticket.dto.user.LoginReq;
import com.lawencon.ticket.dto.user.LoginRes;
import com.lawencon.ticket.dto.user.UserData;
import com.lawencon.ticket.exception.InvalidLoginException;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.security.JwtComponent;
import com.lawencon.ticket.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtComponent jwtComponent;

	@PostMapping
	public ResponseEntity<LoginRes> Login(@RequestBody LoginReq loginReq) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()))
					.isAuthenticated();
		} catch (Exception e) {
			throw new InvalidLoginException("Salah email atau password");
		}

		User user = userService.checkLogin(loginReq.getEmail());
		String token = jwtComponent.generateToken(Duration.ofDays(1), user.getId().toString());
		LoginRes loginRes = new LoginRes();
		UserData data = new UserData();
		data.setUserEmail(user.getUserEmail());
		data.setToken(token);
		data.setRoleCode(user.getRole().getRoleCode());
		data.setVersion(user.getVersion());
		loginRes.setData(data);

		return new ResponseEntity<LoginRes>(loginRes, HttpStatus.OK);
	}
}
