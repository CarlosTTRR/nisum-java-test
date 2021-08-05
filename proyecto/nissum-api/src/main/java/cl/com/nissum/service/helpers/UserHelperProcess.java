package cl.com.nissum.service.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.com.nissum.dtos.PhoneUserRegisterInput;
import cl.com.nissum.dtos.UserRegisterInputRequest;
import cl.com.nissum.dtos.UserRegisterOutputResponse;
import cl.com.nissum.entities.Userphone;
import cl.com.nissum.entities.Usersnisum;
import cl.com.nissum.exceptions.UserEmailFormatException;
import cl.com.nissum.exceptions.UserEmailRepeatedException;
import cl.com.nissum.exceptions.UserPasswordFormatException;
import cl.com.nissum.repository.UserPhoneReposiory;
import cl.com.nissum.repository.UserRegisterRepository;
import cl.com.nissum.service.JsonWebTokenService;
import cl.com.nissum.utils.validator.EmailValidator;
import cl.com.nissum.utils.validator.PasswordValidator;

@Component
public class UserHelperProcess {
	
	@Autowired
	private EmailValidator emailValidator;
	
	@Autowired
	private PasswordValidator passwordValidator;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRegisterRepository userRepository;
	
	@Autowired
	private UserPhoneReposiory userPhoneRepository;
	
	@Autowired
	private JsonWebTokenService jwtService;
	
	
	public void validateUserInput(UserRegisterInputRequest userRegisterInputRequest) throws UserEmailFormatException, UserPasswordFormatException, UserEmailRepeatedException {
		checkEmail(userRegisterInputRequest.getEmail());
		checkPassword(userRegisterInputRequest.getPassword());
		checkEmailExists(userRegisterInputRequest.getEmail());
	}
	
	public ResponseEntity<Object> saveUser(UserRegisterInputRequest userRegisterInputRequest){
		//List phone user
		List<Userphone> lstUserPhones = new ArrayList<>();
		
		//Object USER DB
		Usersnisum userEntity = Usersnisum.builder()
				.isactive(Boolean.TRUE).createdDate(new Date())
				.lastLogin(new Date()).modifiedDate(new Date())
				.name(userRegisterInputRequest.getName())
				.password(bcryptEncoder.encode(userRegisterInputRequest.getPassword()))
				.token(jwtService.getJWTToken(userRegisterInputRequest.getEmail()))
				.email(userRegisterInputRequest.getEmail())
				.id(UUID.randomUUID())
				.build();
		//Save
		userRepository.saveAndFlush(userEntity);
		
		//Save phone relations
		userRegisterInputRequest.getPhones().
		forEach(p -> lstUserPhones
				.add(Userphone.builder()
				.usersnisum(userEntity)
				.citycode(Short.parseShort(p.getCitycode()))
				.countrycode(Short.parseShort(p.getCountrycode()))
				.number(Long.parseLong(p.getNumber()))
				.id(UUID.randomUUID())
				.build()));
		
		//Save Phones
		userPhoneRepository.saveAllAndFlush(lstUserPhones);
		
		userEntity.setUserphones(lstUserPhones);
		userRepository.saveAndFlush(userEntity);
		
		
		return ResponseEntity.ok(generateOutPutResponse(userRepository.findByEmail(userRegisterInputRequest.getEmail())));
	}
	
	
	
	
	public UserRegisterOutputResponse generateOutPutResponse(Usersnisum userDao) {
		UserRegisterOutputResponse response =
				UserRegisterOutputResponse.builder()
				.name(userDao.getName())
				.email(userDao.getEmail())
				.password("********")
				.phones(new ArrayList<PhoneUserRegisterInput>())
				.id(userDao.getId())
				.created(userDao.getCreatedDate())
				.modified(userDao.getModifiedDate())
				.last_login(userDao.getLastLogin())
				.token(userDao.getToken())
				.isactive(userDao.isIsactive())
				.build();
		
		//Phones
		userDao.getUserphones()
		.forEach(phone -> response.getPhones()
				.add
				(new PhoneUserRegisterInput
				(String.valueOf(phone.getCountrycode()), 
				 String.valueOf(phone.getCitycode()), 
				 String.valueOf(phone.getNumber()))));
		
		
		return response;
	}
	
	
	
	public void checkEmail(String email) throws UserEmailFormatException  {
		if(!emailValidator.isValidFormat(email))
			throw new UserEmailFormatException();
	}
	
	public void checkPassword(String password) throws UserPasswordFormatException {
		if(!passwordValidator.isValidFormat(password))
			throw new UserPasswordFormatException();
	}

	public void checkEmailExists(String email) throws UserEmailRepeatedException {
		if(userRepository.checkEmail(email) > 0)
			throw new UserEmailRepeatedException();
	}
}
