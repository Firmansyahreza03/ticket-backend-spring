package com.lawencon.ticket.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pic_to_customer")
public class PicToCustomer extends BaseModel{
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "pic_id")
	private Employee pic;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getPic() {
		return pic;
	}

	public void setPic(Employee pic) {
		this.pic = pic;
	}

}
