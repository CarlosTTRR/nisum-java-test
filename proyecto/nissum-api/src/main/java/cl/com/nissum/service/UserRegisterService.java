package cl.com.nissum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.com.nissum.dtos.ApiSystemResponse;
import cl.com.nissum.dtos.UserRegisterInputRequest;
import cl.com.nissum.exceptions.UserEmailFormatException;
import cl.com.nissum.exceptions.UserEmailRepeatedException;
import cl.com.nissum.exceptions.UserPasswordFormatException;
import cl.com.nissum.service.helpers.UserHelperProcess;

@Service
public class UserRegisterService {

	@Autowired
	UserHelperProcess userHelperProcess;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Object> processAndSave(UserRegisterInputRequest userRegisterInputRequest) {
		try {
			//Valid
			userHelperProcess.validateUserInput(userRegisterInputRequest);			
			return userHelperProcess.saveUser(userRegisterInputRequest);
			
		}catch(UserEmailFormatException | UserPasswordFormatException | UserEmailRepeatedException e) {
			return new ResponseEntity(new ApiSystemResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		catch(Exception genException) {
			return new ResponseEntity(new ApiSystemResponse(genException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
