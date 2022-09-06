package com.lawencon.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_m_product")
public class MasterProduct extends BaseModel {
	@Column(name = "product_name", length = 255)
	private String productName;

	@Column(name = "product_code", length = 4, unique = true)
	private String productCode;

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

}
