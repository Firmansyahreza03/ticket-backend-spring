package com.lawencon.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_thread_hdr")
public class ThreadHeader extends BaseModel {
	@Column(length = 20)
	private String code;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 255)
	private String title;

	@ManyToOne
	@JoinColumn(name = "pic_to_user_id")
	private PicToCustomer picToCustomer;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "priority_id")
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "product_customer_id")
	private CustomerProduct productCustomer;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PicToCustomer getPicToCustomer() {
		return picToCustomer;
	}

	public void setPicToCustomer(PicToCustomer picToCustomer) {
		this.picToCustomer = picToCustomer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public CustomerProduct getProductCustomer() {
		return productCustomer;
	}

	public void setProductCustomer(CustomerProduct productCustomer) {
		this.productCustomer = productCustomer;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
