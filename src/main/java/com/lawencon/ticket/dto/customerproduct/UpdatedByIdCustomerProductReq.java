package com.lawencon.ticket.dto.customerproduct;

import javax.validation.constraints.NotNull;

public class UpdatedByIdCustomerProductReq {
	@NotNull(message = "Id harus diisi")
	private Long id;
	@NotNull(message = "Pelanggan tidak boleh kosong")
	private Long customerId;
	@NotNull(message = "Produk tidak boleh kosong")
	private Long productId;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
