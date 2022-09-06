package com.lawencon.ticket.dto.file;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertFileReq {

	@NotBlank(message = "File harus diisi")
	private String fileName;

	@NotBlank(message = "Extensi file harus diisi")
	private String extension;

	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
