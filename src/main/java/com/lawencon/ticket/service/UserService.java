package com.lawencon.ticket.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.user.ChangePassReq;
import com.lawencon.ticket.dto.user.ChangePassRes;
import com.lawencon.ticket.dto.user.FindAllUserRes;
import com.lawencon.ticket.dto.user.FindByIdUserRes;
import com.lawencon.ticket.dto.user.InsertUserReq;
import com.lawencon.ticket.dto.user.UpdateUserReq;
import com.lawencon.ticket.model.User;

public interface UserService extends UserDetailsService {
	FindByIdUserRes findById(Long id) throws Exception;

	FindAllUserRes findAll() throws Exception;

	InsertRes insert(InsertUserReq user) throws Exception;

	UpdateRes updateById(UpdateUserReq user) throws Exception;

	DeleteRes deleteById(Long id) throws Exception;

	User checkLogin(String email) throws Exception;

	ChangePassRes changePass(ChangePassReq passReq) throws Exception;
}
