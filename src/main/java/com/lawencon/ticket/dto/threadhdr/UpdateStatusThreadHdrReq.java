package com.lawencon.ticket.dto.threadhdr;

import javax.validation.constraints.NotNull;

public class UpdateStatusThreadHdrReq {
	@NotNull(message = "Id harus diisi")
	private Long id;
	private Long updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
