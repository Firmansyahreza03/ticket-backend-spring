package com.lawencon.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_status")
public class Status extends BaseModel {
	@Column(name = "status_name", length = 10)
	private String statusName;

	@Column(name = "status_code", length = 4, unique = true)
	private String statusCode;

	@Column(name = "status_point")
	private Long statusPoint;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Long getStatusPoint() {
		return statusPoint;
	}

	public void setStatusPoint(Long statusPoint) {
		this.statusPoint = statusPoint;
	}

}
