package cl.com.nissum.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.com.nissum.utils.validator.EmailValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmailValidatorTest {

	@Autowired
	EmailValidator emailValidator;

	@ParameterizedTest(name = "#{index} - Run test with email = {0}")
	@MethodSource("emailOkList")
	void testEmailValidationOk(String email) {
		assertTrue(emailValidator.isValidFormat(email));
	}

	@ParameterizedTest(name = "#{index} - Run test with email = {0}")
	@MethodSource("emailErrList")
	void testEmailValidationError(String email) {
		assertFalse(emailValidator.isValidFormat(email));
	}

	// Ok emails
	static Stream<String> emailOkList() {
		return Stream.of("carlos.reyes@nissum.cl", "yulireyes@nissum.cl", "abc123@nissum.cl", "abc123@nissum.com.cl");
	}

	// Error's emails
	static Stream<String> emailErrList() {
		return Stream.of("carlosreyes#nissum.cl", "yulireyesnissum.cl", "abc123~@nn.cl","abc123@nnom.cll", "abc123@nnom.com.pe", "abc123@nnom.cl.cl.cl.com", "abc123@nnom.aaa", "abc123@.aaa");
	}
}
