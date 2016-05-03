package ita.support.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResultModel {
	
	public String authResult;
	public String role;
	public String message;
	
	@JsonCreator
	public AuthResultModel(@JsonProperty("authResult") String authResult, @JsonProperty("role") String role, 
					@JsonProperty("message") String message) {
		this.authResult = authResult;
		this.role = role;
		this.message = message;
	}

	public AuthResultModel() {

	}

}
