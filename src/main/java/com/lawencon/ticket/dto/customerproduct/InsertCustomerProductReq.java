package com.lawencon.ticket.dto.customerproduct;

import javax.validation.constraints.NotNull;

public class InsertCustomerProductReq {
	@NotNull(message = "Pelanggan tidak boleh kosong")
	private Long customerId;

	@NotNull(message = "Produk tidak boleh kosong")
	private Long productId;

	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
