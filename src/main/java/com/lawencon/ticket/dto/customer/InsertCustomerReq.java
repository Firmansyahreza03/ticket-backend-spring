package com.lawencon.ticket.dto.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lawencon.ticket.dto.user.InsertUserReq;

public class InsertCustomerReq {
	@NotBlank(message = "Nama depan tidak boleh kosong")
	private String firstName;

	private String lastName;

	@NotBlank(message = "Nomor HP tidak boleh kosong")
	private String phoneNumb;
	
	@NotBlank(message = "Alamat tidak boleh kosong")
	private String address;

	@NotNull(message = "Pelanggan harus terdaftar di suatu perusahaan")
	private Long companyId;

	@NotNull(message = "Email harus diisi")
	private InsertUserReq user;

	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	private String fileName;

	private String fileExt;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumb() {
		return phoneNumb;
	}

	public void setPhoneNumb(String phoneNumb) {
		this.phoneNumb = phoneNumb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

}
