package com.lawencon.ticket.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer_product")
public class CustomerProduct extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private MasterProduct product;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MasterProduct getProduct() {
		return product;
	}

	public void setProduct(MasterProduct product) {
		this.product = product;
	}

}
