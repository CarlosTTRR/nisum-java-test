package cl.com.nissum.dtos;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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
public class PhoneUserRegisterInput implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Set a Country Code")
	@Range(min=1, max = 9999)
	@Digits(message = "Not valid Country Code.", fraction = 0, integer = 4)
	private String countrycode; 
	
	@Digits(message = "Not valid City Code.", fraction = 0, integer = 4)
	@Range(min=1, max = 9999)
	private String citycode;

	@Digits(message = "Not valid Number Phone", fraction = 0, integer = 25)
	private String number;

}
