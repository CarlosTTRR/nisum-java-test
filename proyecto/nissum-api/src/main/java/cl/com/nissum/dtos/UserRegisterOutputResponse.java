package cl.com.nissum.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRegisterOutputResponse {
	
private static final long serialVersionUID = 1L;
	
	private UUID id;

	private String name,email,password,token;
	
	private List<PhoneUserRegisterInput> phones = new ArrayList<>();
	
	private Date created, modified, last_login;
	
	private boolean isactive;
	
}
