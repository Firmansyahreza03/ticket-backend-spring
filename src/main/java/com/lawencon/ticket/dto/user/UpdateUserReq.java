package com.lawencon.ticket.dto.user;

import javax.validation.constraints.NotNull;

public class UpdateUserReq {
	@NotNull(message = "Id harus diisi")
	private Long id;
	private String userPass;
	private Long roleId;
	private Boolean isActive;
	private Long fileId;

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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

}
