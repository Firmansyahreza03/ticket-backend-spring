package com.lawencon.ticket.dto.status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertStatusReq {
	
	@NotBlank(message = "Nama status tidak boleh kosong")
	private String statusName;
	
	@NotBlank(message = "Kode status tidak boleh kosong")
	private String statusCode;
	
	@NotNull(message = "Poin tidak boleh kosong")
	private Long statusPoint;
	
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Long getStatusPoint() {
		return statusPoint;
	}

	public void setStatusPoint(Long statusPoint) {
		this.statusPoint = statusPoint;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
