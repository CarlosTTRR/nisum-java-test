package cl.com.nissum.apicontroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cl.com.nissum.service.JsonWebTokenService;

@SpringBootTest
@AutoConfigureMockMvc
public class NissumRestApiTokenTest {
	
	@Autowired
	JsonWebTokenService jwtService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    void generateAndValidToken() throws Exception {
        String token = jwtService.getJWTToken("carlos@nissum.cl");
        assertNotNull(token);
        mockMvc.
        perform(MockMvcRequestBuilders.get("/nisum/api/v1/hellouser")
        		.header("Authorization","Bearer "+ token))
        		.andExpect(status().isOk());
    }

}
