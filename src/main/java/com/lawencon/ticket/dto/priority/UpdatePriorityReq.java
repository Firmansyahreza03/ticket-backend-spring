package com.lawencon.ticket.dto.priority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdatePriorityReq {
	@NotNull(message = "Id harus diisi")
	private Long id;
	@NotBlank(message = "Nama prioritas tidak boleh kosong")
	private String priorityName;
	@NotNull(message = "Poin prioritas tidak boleh kosong")
	private Long priorityPoint;
	private Long updatedBy;
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	@NotNull(message = "Version harus diisi")
	private Integer version;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public Long getPriorityPoint() {
		return priorityPoint;
	}

	public void setPriorityPoint(Long priorityPoint) {
		this.priorityPoint = priorityPoint;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
