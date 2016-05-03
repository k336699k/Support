package ita.support.security;

import ita.support.security.model.AuthResultModel;
import ita.support.security.model.CredentialsModel;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;
import ita.support.security.user.UserAuth;



public interface ISecurityFacade {

	boolean updatePassword(UserChangePasswordModel userChangePassword);

	public UserAuth geCurrentPrincipal();

	AuthResultModel authenticateUser(CredentialsModel model);

	boolean checkEmail(UserCheckEmailModel userCheckEmailModel);

}
