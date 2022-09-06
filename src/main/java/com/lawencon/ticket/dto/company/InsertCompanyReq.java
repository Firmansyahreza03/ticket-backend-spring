package com.lawencon.ticket.dto.company;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertCompanyReq {

	@NotBlank(message = "Nama perusahaan tidak boleh kosong")
	private String companyName;

	@NotBlank(message = "Email tidak boleh kosong")
	@Email(message = "Email salah")
	private String email;

	@NotBlank(message = "Alamat tidak boleh kosong")
	private String address;

	@NotNull(message = "Harus pilih antara true dan false")
	private Boolean isActive;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
