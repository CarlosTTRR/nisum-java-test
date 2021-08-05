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

import cl.com.nissum.utils.validator.PasswordValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PasswordValidatorTest {

	@Autowired
	PasswordValidator passwordValidator;

	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
	@MethodSource("passwordOkList")
	void testPasswordValidationOk(String password) {
		assertTrue(passwordValidator.isValidFormat(password));
	}

	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
	@MethodSource("passwordErrList")
	void testPasswordValidationError(String password) {
		assertFalse(passwordValidator.isValidFormat(password));
	}

	// Ok emails
	static Stream<String> passwordOkList() {
		return Stream.of("A22aaaaaaaa", "99Basasasas", "qwqwqwqwqC32", "9aaaaFcssc9");
	}

	// Error's emails
	static Stream<String> passwordErrList() {
		return Stream.of("123456", "carlostomas", "99999RCAaaaa", "9RCAaaaa", "9aaaaFcsscF", "4CaaFcsscF");
	}

}
