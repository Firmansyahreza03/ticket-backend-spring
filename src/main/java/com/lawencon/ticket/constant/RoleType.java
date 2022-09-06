package com.lawencon.ticket.constant;

public enum RoleType {
	SUPER_ADMIN("ESA"), PIC("PIC"), CUSTOMER("CUST");

	private String code;

	private RoleType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}
