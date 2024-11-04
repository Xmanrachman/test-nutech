package com.nutech.test.sims.ppob.service.transaction;


import java.util.List;
import java.util.Optional;


import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;
import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.response.ResponseTransactionHis;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseBalanceDto;
import com.nutech.test.sims.ppob.dto.response.result.ResponseTransactionHistoryDto;
import com.nutech.test.sims.ppob.repository.TransactionRepository;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

@Service
public class InqueryTransactionService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseHandlingError handlingError;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	
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
	
	
	
	public ResponseGeneral getTransactionHistory(Integer offset, Integer limit) {
		
		ResponseGeneral response = new ResponseGeneral();
		ResponseTransactionHis responseTransHis = new ResponseTransactionHis();
		List<ResponseTransactionHistoryDto> transactoinHisList;
		if (limit == 0 ) {
			List<TransactionEntity> transactionHis = transactionRepo.findAll();
			
			responseTransHis.setOffset(offset);
			responseTransHis.setLimit(limit);
			responseTransHis.setRecords(transactionHis);
			
			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Get History Berhasil");
			response.setData(responseTransHis);
			
		} else {
			List<TransactionEntity> transactionHis = transactionRepo.findAll();
			
			responseTransHis.setOffset(offset);
			responseTransHis.setLimit(limit);
			responseTransHis.setRecords(transactionHis);
			
			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Get History Berhasil");
			response.setData(responseTransHis);
		}
		
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
