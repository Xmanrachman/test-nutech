package com.nutech.test.sims.ppob.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.dto.master.user.UpdateUserRequestDto;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.master.UpdateProfileService;

@RestController
@RequestMapping("/auth")
public class UpdateProfileController {
	
	@Autowired
	private UpdateProfileService updateProfileService;
	
	@PutMapping("/profile/update")
	public ResponseGeneral updateProfile(@RequestBody UpdateUserRequestDto request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return updateProfileService.updateProfileUser(request, authentication);
	}
	
	@PutMapping("/profile/image")
	public ResponseGeneral updateProfileImage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return updateProfileService.updateProfileImage( authentication);
	}
	

}
