package cl.com.nissum.apicontroller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import cl.com.nissum.dtos.PhoneUserRegisterInput;
import cl.com.nissum.dtos.UserRegisterInputRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-db.sql")
public class NissumRestApiControllerTest {
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	@DisplayName("Test Welcome Api <3")
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/nisum/api/v1/welcome")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("welcome")));
	}
	
	@Test
	@DisplayName("Test Welcome Api Fail")
	void shouldFailAccessMessage() throws Exception {
		this.mockMvc.perform(get("/nisum/api/v1/aaaaa")).andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@ParameterizedTest(name = "#{index} - Run test with user = {0}.name")
	@MethodSource("usersRegisterMustOk")
	void testApiUpUsersOk(UserRegisterInputRequest user) throws Exception {
		this.mockMvc.perform(
				post("/nisum/api/v1/registeruser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converterToJsonString(user))
				)
		
		.andDo(print())
		.andExpect(status().isOk()
		);
		
	}
	
	@ParameterizedTest(name = "#{index} - Run test with bad user = {0}.name")
	@MethodSource("usersRegisterMustError")
	void testApiUpUsersBad(UserRegisterInputRequest user) throws Exception {
		this.mockMvc.perform(
				post("/nisum/api/v1/registeruser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converterToJsonString(user))
				)
		
		.andDo(print())
		.andExpect(status().isBadRequest()
		);
		
	}
	
	@Test
	@DisplayName("Email Repeated")
	void testApiRepeatedEmail() throws Exception {
		this.mockMvc.perform(
				post("/nisum/api/v1/registeruser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converterToJsonString(UserRegisterInputRequest.builder()
						  .name("Carlos EE")
						  .password("A22aaaaa")
						  .email("carlostomasw@domain.cl")
						  .phones(
								  Arrays.asList(
										  new PhoneUserRegisterInput("1","4","9178"),
										  new PhoneUserRegisterInput("1","2","1234")))
						  .build()))
				)
		
		.andDo(print())
		.andExpect(status().isOk()
		);
		
		this.mockMvc.perform(
				post("/nisum/api/v1/registeruser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converterToJsonString(UserRegisterInputRequest.builder()
						  .name("Juan EE")
						  .password("B22aaaaa")
						  .email("carlostomasw@domain.cl")
						  .phones(
								  Arrays.asList(
										  new PhoneUserRegisterInput("1","4","9178"),
										  new PhoneUserRegisterInput("1","2","1234")))
						  .build()))
				)
		
		.andDo(print())
		.andExpect(status().isBadRequest()
		);
	}
	
	
	static Stream<UserRegisterInputRequest> usersRegisterMustOk(){
		return Stream.of(UserRegisterInputRequest.builder()
				  .name("Carlos")
				  .password("A22aaaaa")
				  .email("carlostomasw@domain.cl")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","4","9178"),
								  new PhoneUserRegisterInput("1","2","1234")))
				  .build()
				  
				  ,
				  
				  UserRegisterInputRequest.builder()
				  .name("Maria")
				  .password("A22aaaaa")
				  .email("carlostomasw2@domain.cl")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","5","91788"),
								  new PhoneUserRegisterInput("1","6","1234")))
				  .build()
				);
	}
	
	static Stream<UserRegisterInputRequest> usersRegisterMustError(){
		return Stream.of(UserRegisterInputRequest.builder()
				  .name("Juan")
				  .password("")
				  .email("carlostomasw@domain.cl")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","4","91788"),
								  new PhoneUserRegisterInput("1","2","1234")))
				  .build()
				  
				  ,
				  
				  UserRegisterInputRequest.builder()
				  .name("Maria")
				  .password("A222aaaaa")
				  .email("carlostomaswqwq2@domain.cl")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","5","91788"),
								  new PhoneUserRegisterInput("1","6","1234")))
				  .build()
				  ,
				  
				  UserRegisterInputRequest.builder()
				  .name("Maria")
				  .password("A222aaaaa")
				  .email("carlostomaswqwq2@domain.com")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","5","91788"),
								  new PhoneUserRegisterInput("1","6","1234")))
				  .build()
				  ,
				  
				  UserRegisterInputRequest.builder()
				  .name("Maria")
				  .password("A22aaaaa")
				  .email("carlostomaswqwq2@domain.cl")
				  .phones(null)
				  .build()		
				  
				  ,	  
				  UserRegisterInputRequest.builder()
				  .name("")
				  .password("A22aaaaa")
				  .email("carlostomaswqwq2@domain.cl")
				  .phones(null)
				  .build()
				  
				  ,
				  
				  UserRegisterInputRequest.builder()
				  .name("Maria")
				  .password("A222aaaaa")
				  .email("")
				  .phones(
						  Arrays.asList(
								  new PhoneUserRegisterInput("1","5","91788"),
								  new PhoneUserRegisterInput("1","6","1234")))
				  .build()
				);
	}
	
	
	private String converterToJsonString(UserRegisterInputRequest user) {
		Gson gson = new Gson();
		return gson.toJson(user);
	}

}