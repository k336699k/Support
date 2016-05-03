package ita.support.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialsModel {

	public String username;
	public String password;

	@JsonCreator
	public CredentialsModel(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}

	public CredentialsModel() {

	}
}
