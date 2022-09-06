package com.lawencon.ticket.dto.priority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertPriorityReq {
	
	@NotBlank(message = "Nama prioritas tidak boleh kosong")
	private String priorityName;
	
	@NotBlank(message = "Kode prioritas tidak boleh kosong")
	private String priorityCode;
	
	@NotNull(message = "Poin prioritas tidak boleh kosong")
	private Long priorityPoint;
	
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	public Long getPriorityPoint() {
		return priorityPoint;
	}

	public void setPriorityPoint(Long priorityPoint) {
		this.priorityPoint = priorityPoint;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
