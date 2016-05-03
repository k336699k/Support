package ita.support.security.user.managers.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ita.support.dba.DaoFtException;
import ita.support.dba.dao.security.ISecurityDao;
import ita.support.dba.entities.security.User;
import ita.support.security.model.AuthResultModel;
import ita.support.security.model.CredentialsModel;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;
import ita.support.security.user.UserAuth;
import ita.support.security.user.managers.IUserMng;

@Service
@Transactional
public class UserMngImpl implements IUserMng {

	private Logger log = LoggerFactory.getLogger(UserMngImpl.class);

	@Autowired
	private ISecurityDao userDAO;

	@Autowired
	private UserDetailsService userService;

	@Override
	public boolean updatePassword(UserChangePasswordModel userChangePassword) {
		User user = null;
		boolean result = false;

		user = userDAO.getUserbyUserNameAndPassword(userChangePassword.username, userChangePassword.password);
		if (user != null) {
			user.setPassword(userChangePassword.newPassword);
			try {
				userDAO.update(user);
				result = true;
			} catch (DaoFtException e) {
				log.error("Error! Exception:" + e.getLocalizedMessage());
			}
		}

		return result;
	}

	@Override
	public AuthResultModel authenticateUser(CredentialsModel credentialsModel) {
		String authResult = "";
		String role = "";
		String message = "";

		UserAuth user = (UserAuth) userService.loadUserByUsername(credentialsModel.username);
		if (user == null) {
			authResult = "false";
			role = "null";
			message = "User with this login not found";
		}
		if (user != null && !user.getPassword().equals(credentialsModel.password)) {
			authResult = "false";
			role = user.getAuthorities().toString();
			message = "Wrong password for this login";
		}
		if (user != null && user.getPassword().equals(credentialsModel.password)) {
			authResult = "true";
			role = user.getAuthorities().toString();
			message = "Seems like all is OK. So strange...";
		}

		AuthResultModel resultModel = new AuthResultModel(authResult, role, message);
		return resultModel;
	}

	@Override
	public boolean checkEmail(UserCheckEmailModel userCheckEmailModel) {
		User user = null;
		boolean result = false;
		user = userDAO.getUserbyEmail(userCheckEmailModel.email);
		if (user != null) {
			result = true;
		}
		return result;
	}

}
