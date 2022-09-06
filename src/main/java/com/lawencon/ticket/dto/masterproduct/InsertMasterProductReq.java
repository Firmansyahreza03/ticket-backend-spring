package com.lawencon.ticket.dto.masterproduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertMasterProductReq {
	
	@NotBlank(message = "Nama produk tidak boleh kosong")
	private String productName;
	
	@NotBlank(message = "Kode produk tidak boleh kosong")
	private String productCode;
	
	@NotNull(message = "Harus pilih antara true atau false")
	private Boolean isActive;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
