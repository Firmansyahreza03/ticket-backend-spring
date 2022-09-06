package com.lawencon.ticket.dto.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateRoleReq {

	@NotNull(message = "Id tidak boleh kosong")
	private Long id;
	@NotBlank(message = "Nama role tidak boleh kosong")
	private String roleName;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
