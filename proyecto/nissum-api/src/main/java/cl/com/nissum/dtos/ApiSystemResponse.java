package cl.com.nissum.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiSystemResponse implements Serializable {

	private static final long serialVersionUID = -6594533942128451451L;
	
	@JsonProperty("mensaje")
	private String mensaje;
}
