package com.lawencon.ticket.dto.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCustomerReq {

	@NotNull(message = "Id harus diisi")
	private Long id;
	
	@NotBlank(message = "Nama depan harus diisi")
	private String firstName;
	
	private String lastName;
	
	@NotBlank(message = "Alamat tidak boleh kosong")
	private String address;
	
	@NotNull(message = "Pelanggan harus terdaftar di suatu perusahaan")
	private Long companyId;
		
	private Long updatedBy;
	
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;
	
	private String fileName;
	private String fileExt;

	@NotNull(message = "Version harus diisi")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
