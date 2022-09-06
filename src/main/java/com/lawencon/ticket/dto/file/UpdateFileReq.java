package com.lawencon.ticket.dto.file;

import javax.validation.constraints.NotNull;

public class UpdateFileReq {

	@NotNull(message = "Id tidak boleh kosong")
	private Long id;

	private String fileName;
	private String extension;

	@NotNull(message = "Version harus diisi")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
