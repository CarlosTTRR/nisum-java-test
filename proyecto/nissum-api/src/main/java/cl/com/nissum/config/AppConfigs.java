package cl.com.nissum.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.com.nissum.utils.NissumAppConstants;
import cl.com.nissum.utils.validator.EmailValidator;
import cl.com.nissum.utils.validator.PasswordValidator;

@Configuration
public class AppConfigs {

	@Bean("patternEmailValidation")
	public Pattern emailValidatorPattern() {
		return Pattern.compile(NissumAppConstants.REGEX_EMAIL_VALIDATION);
	}
	
	@Bean("emailValidatorBean")
	public EmailValidator emailValidatorBean() {
		return new EmailValidator(this.emailValidatorPattern());
	}
	
	@Bean("patternPasswordValidation")
	public Pattern passwordValidatorPattern() {
		return Pattern.compile(NissumAppConstants.REGEX_PASSWORD_VALIDATION);
	}
	
	@Bean("passwordValidatorBean")
	public PasswordValidator passwordValidatorBean() {
		return new PasswordValidator(this.passwordValidatorPattern());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
