package ita.support.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePasswordModel {

	public String username;
	public String password;
	public String newPassword;

	@JsonCreator
	public UserChangePasswordModel(@JsonProperty("username") String username, @JsonProperty("password") String password,
					@JsonProperty("newPassword") String newPassword) {
		this.username = username;
		this.password = password;
		this.newPassword = newPassword;
	}

	public UserChangePasswordModel() {

	}

}
