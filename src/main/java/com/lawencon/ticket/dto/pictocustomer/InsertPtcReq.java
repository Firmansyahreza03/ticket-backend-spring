package com.lawencon.ticket.dto.pictocustomer;

import javax.validation.constraints.NotNull;

public class InsertPtcReq {
	@NotNull(message = "Pelanggan tidak boleh kosong")
	private Long customerId;

	@NotNull(message = "PIC tidak boleh kosong")
	private Long picId;

	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
