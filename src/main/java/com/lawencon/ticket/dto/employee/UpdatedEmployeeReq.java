package com.lawencon.ticket.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lawencon.ticket.dto.user.UpdateUserReq;

public class UpdatedEmployeeReq {

	@NotNull(message = "Id harus diisi")
	private Long id;
	@NotBlank(message = "Nama lengkap tidak boleh kosong")
	private String fullName;
	@NotNull(message = "Email tidak boleh kosong")
	private UpdateUserReq user;
	private String fileName;
	private String fileExt;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UpdateUserReq getUser() {
		return user;
	}

	public void setUser(UpdateUserReq user) {
		this.user = user;
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

}
