package ita.support.security.user.managers;

import ita.support.security.model.AuthResultModel;
import ita.support.security.model.CredentialsModel;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;

public interface IUserMng {
	boolean updatePassword(UserChangePasswordModel userChangePassword);
	
	AuthResultModel authenticateUser(CredentialsModel userChangePassword);
	
	boolean checkEmail(UserCheckEmailModel userCheckEmailModel);
}
