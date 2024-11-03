package com.nutech.test.sims.ppob.service.transaction;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseBalanceDto;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

@Service
public class InqueryTransactionService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseHandlingError handlingError;
	
	
	public ResponseGeneral getBalanceUser(Authentication authentication) {
		
		ResponseGeneral response = new ResponseGeneral();
		ResponseBalanceDto responseBalance = new ResponseBalanceDto();
		UserEntity currentUser = chechCurrentUser(authentication);
		
        Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());
        
        if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}
        responseBalance = infoResultData(userFind.get(), responseBalance);
        
        response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Get Balance Berhasil");
		response.setData(responseBalance);
        
		return response;
	}
	
	
	private ResponseBalanceDto infoResultData(UserEntity user, ResponseBalanceDto responseResult) {
		responseResult.setBalance(user.getSaldo());
		return responseResult;

	}

	private UserEntity chechCurrentUser(Authentication auth) {
		UserEntity user = (UserEntity) auth.getPrincipal();

		return user;
	}
	

}
