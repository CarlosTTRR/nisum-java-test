package cl.com.nissum.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidator implements IValidatorFormat {

	private final Pattern patternPasswordValidation;

	@Autowired
	public PasswordValidator(@Qualifier("patternPasswordValidation")Pattern patternPasswordValidation) {
		this.patternPasswordValidation = patternPasswordValidation;
	}

	public boolean isValidFormat(String inputPassword) {
		Matcher matcher = patternPasswordValidation.matcher(inputPassword);
		return matcher.matches();
	}

}
