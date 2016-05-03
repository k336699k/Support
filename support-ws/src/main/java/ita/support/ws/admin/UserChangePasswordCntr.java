package ita.support.ws.admin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ita.support.security.ISecurityFacade;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;
import ita.support.security.validator.ChangePasswordValidator;
import ita.support.security.validator.utils.IUtilValidatorSecurity;

@RestController
@RequestMapping("/admin")
public class UserChangePasswordCntr {

	private Logger logger = LoggerFactory.getLogger(UserChangePasswordCntr.class.getName());

	@Autowired
	private ISecurityFacade securityFacade;

	@Autowired
	private ChangePasswordValidator changePasswordValidator;

	@Autowired
	private IUtilValidatorSecurity util;

	@RequestMapping(path = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody UserChangePasswordModel userChangePassword,
					BindingResult bindingResult) {

		changePasswordValidator.validate(userChangePassword, bindingResult);
		if (userChangePassword.username != null && userChangePassword.password != null
						&& !bindingResult.hasErrors()) {
			if (securityFacade.updatePassword(userChangePassword)) {
				logger.debug("Password changed " + userChangePassword.username);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				logger.debug("Password did not change  " + userChangePassword.username);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
			return new ResponseEntity<Map<String, String>>(util.getMessages(bindingResult), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(path = "/checkEmail", method = RequestMethod.POST)
	public ResponseEntity<?> checkEmail(@RequestBody UserCheckEmailModel userCheckEmailModel,
					BindingResult bindingResult) {

		if (securityFacade.checkEmail(userCheckEmailModel)) {
			logger.debug("User found  " + userCheckEmailModel.email);
			return new ResponseEntity<>("Email sent", HttpStatus.OK);
		} else {
			logger.debug("User was not found  " + userCheckEmailModel.email);
			return new ResponseEntity<>("User was not found  " + userCheckEmailModel.email, HttpStatus.OK);
		}
	}
}
