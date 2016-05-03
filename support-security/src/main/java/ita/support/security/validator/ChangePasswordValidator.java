package ita.support.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.validator.utils.IUtilValidatorSecurity;

@Component
public class ChangePasswordValidator implements Validator {

	@Autowired
	private IUtilValidatorSecurity util;

	@Override

	public boolean supports(Class<?> clazz) {
		return UserChangePasswordModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		UserChangePasswordModel userChangePassword = (UserChangePasswordModel) object;

		// NEWPASSWORD
		if (userChangePassword.newPassword == null || userChangePassword.newPassword.equals("")) {
			util.getError(errors, "userChangePassword.newPassword", "user.password.empty");
		} else
			if (userChangePassword.newPassword.length() < 6 || userChangePassword.newPassword.length() > 25) {
				util.getError(errors, "userChangePassword.newPassword", "user.password.length");
			} else
				if (!util.checkRegularExpressions("user.password.check_characters", userChangePassword.newPassword)) {
					util.getError(errors, "userChangePassword.newPassword", "user.password.wrong");
				} else
					if (!util.checkRegularExpressions("user.password.check_login_old_password", userChangePassword.newPassword)) {
						util.getError(errors, "userChangePassword.newPassword", "user.passwords.not_contain");
					} else
						if (userChangePassword.newPassword.equals(userChangePassword.username) == true
										|| userChangePassword.newPassword.equals(userChangePassword.password) == true) {
							util.getError(errors, "userChangePassword.newPassword", "user.passwords.not_equal");
						}

	}

}
