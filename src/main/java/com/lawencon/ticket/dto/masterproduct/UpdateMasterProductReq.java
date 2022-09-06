package com.lawencon.ticket.dto.masterproduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateMasterProductReq {

	@NotNull(message = "Id harus diisi")
	private Long id;
	@NotBlank(message = "Nama produk tidak boleh kosong")
	private String productName;
	private Long updatedBy;
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	@NotNull(message = "Version harus diisi")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
