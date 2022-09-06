package com.lawencon.ticket.dto.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertRoleReq {
	@NotBlank(message = "Nama role tidak boleh kosong")
	private String roleName;
	
	@NotBlank(message = "Kode role tidak boleh kosong")
	private String roleCode;
	
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
