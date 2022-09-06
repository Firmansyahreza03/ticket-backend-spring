package com.lawencon.ticket.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.ticket.exception.InvalidLoginException;

public abstract class BaseServiceImpl {
	public Long getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new InvalidLoginException("Invalid username/password");
		}
		
		Long id = Long.valueOf(authentication.getPrincipal().toString());
		return id;
	}
}
