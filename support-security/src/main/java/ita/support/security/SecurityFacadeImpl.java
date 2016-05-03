package ita.support.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ita.support.security.model.AuthResultModel;
import ita.support.security.model.CredentialsModel;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;
import ita.support.security.user.UserAuth;
import ita.support.security.user.managers.IUserMng;



@Component
public class SecurityFacadeImpl implements ISecurityFacade {

	@Autowired
	private IUserMng userMng;

	@Override
	public boolean updatePassword(UserChangePasswordModel userChangePassword) {
		return userMng.updatePassword(userChangePassword);
	}
	
	@Override
	public AuthResultModel authenticateUser(CredentialsModel model) {
		return userMng.authenticateUser(model);
	}

	@Override
	public UserAuth geCurrentPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserAuth) {
			return (UserAuth) principal;
		}
		return new UserAuth();
	}

	
	
	@Override
	public boolean checkEmail(UserCheckEmailModel userCheckEmailModel) {
		return userMng.checkEmail(userCheckEmailModel);
	}

}
