package cl.com.nissum.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements IValidatorFormat{

	private final Pattern patternEmailValidation;
	
	@Autowired
	public EmailValidator(@Qualifier("patternEmailValidation")Pattern patternEmailValidation) {
		this.patternEmailValidation = patternEmailValidation;
	}
	
	public boolean isValidFormat(String inputEmail) {
		Matcher matcher = patternEmailValidation.matcher(inputEmail);
		return matcher.matches();
	}
}
