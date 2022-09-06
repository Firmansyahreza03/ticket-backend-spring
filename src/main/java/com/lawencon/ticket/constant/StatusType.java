package com.lawencon.ticket.constant;

public enum StatusType {
	OPN("Open"),CLS("Closed"),ROPN("Re-open");
	
	private String name;
	
	private StatusType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
