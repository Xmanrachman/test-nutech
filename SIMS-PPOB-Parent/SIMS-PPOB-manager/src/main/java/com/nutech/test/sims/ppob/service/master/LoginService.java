package com.nutech.test.sims.ppob.service.master;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.LoginRequestDto;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.utility.UtilityPatternMatches;

@Service
public class LoginService {
	
	@Autowired
	private ResponseHandlingError handlingError;
	@Autowired
	private UtilityPatternMatches utilityPatternMatches;
	
	@Autowired
	private UserRepository userRepo;
	
	public ResponseGeneral loginProcess(LoginRequestDto request) {
		
		ResponseGeneral response = new ResponseGeneral();
		String regexPattern = "^(.+)@(\\S+)$";
		
		if (request == null) {
			response = handlingError.errorBadRequest();
			return response;
		}

		boolean matches = utilityPatternMatches.patternMatches(request.getEmail(), regexPattern);
		if (matches != true) {
			response = handlingError.errorMatcheLengthEmail();
			return response;
		}
		int lengthPassword = request.getPassword().length();
		if (lengthPassword < 8) {
			response = handlingError.errorMatcheLengthPassword();
			return response;
		}
		
		Optional<UserEntity> userOpt = userRepo.findByEmailUserAndPassword(request.getEmail(), request.getPassword());
		
		if (!userOpt.isPresent()) {
			response = handlingError.errorUnAuthorized();
			return response;
		} else if (userOpt.isPresent()) {
			
			//proses create token jwt
			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Login Sukses");
			response.setData("null");
		}
		
		
		return response;
	}

}
