package com.nutech.test.sims.ppob.service.master;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.UpdateUserRequestDto;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseProfileDto;
import com.nutech.test.sims.ppob.mapper.UserMapper;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

@Service
public class UpdateProfileService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private UserMapper userMapper;

	public ResponseGeneral updateProfileUser(UpdateUserRequestDto requestProfileUser, Authentication authentication) {

		ResponseGeneral response = new ResponseGeneral();

		UserEntity currentUser = chechCurrentUser(authentication);

		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}

		UserEntity userEntity = userMapper.updateProfileUser(userFind.get(), requestProfileUser);
		userRepo.saveAndFlush(userEntity);
		if (userEntity.getIdUser() != 0) {
			ResponseProfileDto responseResult = new ResponseProfileDto();
			responseResult = infoDataProfile(userEntity, responseResult);

			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Update Pofile berhasil");
			response.setData(responseResult);

		}

		return response;

	}

	public ResponseGeneral updateProfileImage(Authentication authentication) {
		ResponseGeneral response = new ResponseGeneral();

		UserEntity currentUser = chechCurrentUser(authentication);
		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}
		//proses bisnis
		
		
		return response;

	}

	private ResponseProfileDto infoDataProfile(UserEntity user, ResponseProfileDto responseResult) {

		responseResult.setEmail(user.getEmailUser());
		responseResult.setFirstName(user.getFirstName());
		responseResult.setLastName(user.getLastName());
		responseResult.setProfileImage(user.getProfileImage());

		return responseResult;

	}

	private UserEntity chechCurrentUser(Authentication auth) {
		UserEntity user = (UserEntity) auth.getPrincipal();

		return user;
	}

}
