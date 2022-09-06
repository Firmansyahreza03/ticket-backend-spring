package com.lawencon.ticket.dto.user;

import javax.validation.constraints.NotBlank;

public class LoginReq {
	@NotBlank(message = "Email tidak boleh kosong")
	private String email;
	@NotBlank(message = "Password tidak boleh kosong")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
