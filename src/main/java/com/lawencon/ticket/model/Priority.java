package com.lawencon.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_priority")
public class Priority extends BaseModel {
	@Column(name = "priority_name", length = 10)
	private String priorityName;

	@Column(name = "priority_code", length = 10, unique = true)
	private String priorityCode;

	@Column(name = "priority_point")
	private Long priorityPoint;

	private Long duration;

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
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
