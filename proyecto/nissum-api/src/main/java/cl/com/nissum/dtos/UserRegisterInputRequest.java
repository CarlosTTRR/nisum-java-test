package cl.com.nissum.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterInputRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "User musts contains a name")
	private String name;
	
	@NotEmpty(message = "User musts contains an email")
	@Email
	private String email;
	
	@NotEmpty(message = "User musts set a password")
	private String password;
	
	@NotEmpty(message = "User musts set at least one phone")
	private @Valid List<PhoneUserRegisterInput> phones = new ArrayList<>();

}
