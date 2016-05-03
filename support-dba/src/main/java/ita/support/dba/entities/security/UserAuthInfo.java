package ita.support.dba.entities.security;

public class UserAuthInfo {
	public final String username;
	public final String password;
	public final String role;
	public final String status;

	public UserAuthInfo(String username, String password, String role, String status) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
	}

}
