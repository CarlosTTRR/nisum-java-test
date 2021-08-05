package cl.com.nissum.apicontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.com.nissum.dtos.ApiSystemResponse;
import cl.com.nissum.dtos.UserRegisterInputRequest;
import cl.com.nissum.service.UserRegisterService;

@RestController
@RequestMapping("/nisum/api/v1/")
public class NissumRestApiController {
	
	@Autowired
	UserRegisterService userService;
	
	@GetMapping("/welcome")
	public ResponseEntity<Object> welcome(){
		return ResponseEntity.ok(new ApiSystemResponse("welcome to nissum"));
	}
	
	@PostMapping("/registeruser")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRegisterInputRequest userRegisterInputRequest){
		return userService.processAndSave(userRegisterInputRequest);
	}
	
	//Must protected
	@GetMapping("/hellouser")
	public ResponseEntity<Object> hellouser(){
		return ResponseEntity.ok(new ApiSystemResponse("Hello user you'r logged!"));
	}
}
