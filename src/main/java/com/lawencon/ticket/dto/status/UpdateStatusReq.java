package com.lawencon.ticket.dto.status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateStatusReq {

	@NotNull(message = "Id tidak boleh kosong")
	private Long id;
	@NotBlank(message = "Nama status tidak boleh kosong")
	private String statusName;
	@NotNull(message = "Poin tidak boleh kosong")
	private Long statusPoint;
	private Long updatedBy;
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	@NotNull(message = "Version tidak boleh kosong")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long getStatusPoint() {
		return statusPoint;
	}

	public void setStatusPoint(Long statusPoint) {
		this.statusPoint = statusPoint;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
