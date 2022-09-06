package com.lawencon.ticket.dto.threadhdr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertThreadHdrReq {
	@NotBlank(message = "Deskripsi masalah harus diisi")
	private String description;

	@NotBlank(message = "Judul masalah harus diisi")
	private String title;

	@NotNull(message = "Prioritas masalah harus diisi")
	private Long priorityId;

	@NotNull(message = "Produk harus diisi")
	private Long productId;

	private String fileName;

	private String fileExt;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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
}
