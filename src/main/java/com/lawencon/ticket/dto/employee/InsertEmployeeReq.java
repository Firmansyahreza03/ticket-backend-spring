package com.lawencon.ticket.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lawencon.ticket.dto.user.InsertUserReq;

public class InsertEmployeeReq {
	@NotBlank(message = "Nama lengkap tidak boleh kosong")
	private String fullName;

	@NotNull(message = "Email tidak boleh kosong")
	private InsertUserReq user;

	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	private String fileName;

	private String fileExt;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public InsertUserReq getUser() {
		return user;
	}

	public void setUser(InsertUserReq user) {
		this.user = user;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

}
