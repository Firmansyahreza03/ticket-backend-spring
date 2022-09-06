package com.lawencon.ticket.service;

import com.lawencon.ticket.model.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
}
