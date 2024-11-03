package com.nutech.test.sims.ppob.controller.profile;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseGeneral;

@RestController
@RequestMapping("/auth")
public class UpdateProfileController {
	
	@PutMapping("/profile/update")
	public ResponseGeneral updateProfile() {
		
		return null;
	}
	

}
