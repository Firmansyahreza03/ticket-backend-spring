package com.lawencon.ticket.dto.user;

import javax.validation.constraints.NotBlank;

public class ChangePassReq {
	@NotBlank(message = "Password lama tidak boleh kosong")
	private String oldPass;

	@NotBlank(message = "Password baru tidak boleh kosong")
	private String newPass;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

}
