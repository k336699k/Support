package ita.support.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private SimpleGrantedAuthority role;
	private EUserStatuses status;

	public UserAuth() {
	}

	public UserAuth(String username, String password, EUserRoles role,
					EUserStatuses status) {
		super();
		this.username = username;
		this.password = password;
		this.role = new SimpleGrantedAuthority(role.name());
		this.status = status;
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(this.role);
		return list;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.status == EUserStatuses.OPENED;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.status != EUserStatuses.BLOCKED;
	}

	/**
	 * not used. stub realization
	 */
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	/**
	 * not used. stub realization
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
}
