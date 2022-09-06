package com.lawencon.ticket.dto.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCompanyReq {
	@NotNull(message = "Id tidak boleh kosong")
	private Long id;

	@NotBlank(message = "Alamat tidak boleh kosong")
	private String address;
	private Long updatedBy;
	
	@NotNull(message = "Harus pilih antara true dan false")
	private Boolean isActive;

	@NotNull(message = "Version harus diisi")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
