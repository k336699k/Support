package ita.support.ws.auth;

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
import ita.support.security.model.AuthResultModel;
import ita.support.security.model.CredentialsModel;
import ita.support.security.validator.utils.IUtilValidatorSecurity;
import ita.support.ws.admin.UserChangePasswordCntr;

@RestController
@RequestMapping("/authenticate")
public class AuthorizationCntr {

	private Logger logger = LoggerFactory.getLogger(UserChangePasswordCntr.class.getName());

	@Autowired
	private ISecurityFacade securityFacade;

	@Autowired
	private IUtilValidatorSecurity util;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestBody CredentialsModel model, BindingResult bindingResult) {

		// changePasswordValidator.validate(userChangePassword, bindingResult);

		if (model.username != null && model.password != null
						&& !bindingResult.hasErrors()) {
			AuthResultModel authModel = securityFacade.authenticateUser(model);
			if (authModel != null) {

				logger.debug("Auth in progress for " + model.username);
				return new ResponseEntity<>(authModel, HttpStatus.OK);
			} else {
				logger.debug("Auth get a problem  " + model.username);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} else
			return new ResponseEntity<Map<String, String>>(util.getMessages(bindingResult), HttpStatus.BAD_REQUEST);

	}

}
