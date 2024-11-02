package com.nutech.test.sims.ppob.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.dto.master.user.EntryUserRequestDto;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.master.EntryRegistrationUserService;

@RestController
public class EntryUserController {
	
	@Autowired
	private EntryRegistrationUserService registrationUserService;
	
	@PostMapping("/entry/registration/user")
	public ResponseGeneral entryRegistrationUser(@RequestBody EntryUserRequestDto request) {
		return registrationUserService.entryRegistrationUser(request);
		
	}
	
	

}
