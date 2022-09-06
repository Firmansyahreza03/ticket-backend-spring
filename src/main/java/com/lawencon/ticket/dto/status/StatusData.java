package com.lawencon.ticket.dto.status;

public class StatusData {
	private Long id;
	private String statusName;
	private String statusCode;
	private Long statusPoint;
	private Boolean isActive;
	private Integer version;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
