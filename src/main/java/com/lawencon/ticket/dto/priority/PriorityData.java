package com.lawencon.ticket.dto.priority;

public class PriorityData {
	private Long id;
	private String priorityName;
	private String priorityCode;
	private Long priorityPoint;
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

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	public Long getPriorityPoint() {
		return priorityPoint;
	}

	public void setPriorityPoint(Long priorityPoint) {
		this.priorityPoint = priorityPoint;
	}

}
