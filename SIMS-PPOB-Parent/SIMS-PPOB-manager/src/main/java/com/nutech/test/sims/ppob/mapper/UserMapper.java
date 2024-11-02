package com.nutech.test.sims.ppob.mapper;

import org.springframework.stereotype.Component;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.EntryUserRequestDto;

@Component
public class UserMapper {
	
	
	
	
	public UserEntity registrationUser(EntryUserRequestDto request) {
		
		UserEntity user  = new UserEntity();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailUser(request.getEmail());
		user.setPassword(request.getPassword());
		
		return user;
	}

}
