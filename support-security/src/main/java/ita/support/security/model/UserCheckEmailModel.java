package ita.support.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCheckEmailModel {

	public String email;

	@JsonCreator
	public UserCheckEmailModel(@JsonProperty("email") String email) {
		this.email = email;
	}

	public UserCheckEmailModel() {

	}

	@Override
	public String toString() {
		return "UserCheckEmail [email=" + email + "]";
	}

}
