package com.lawencon.ticket.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertUserReq {

	@NotBlank(message = "Email tidak boleh kosong")
	@Email(message = "Email salah")
	private String userEmail;

	@NotNull(message = "Role tidak boleh kosong")
	private Long roleId;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
