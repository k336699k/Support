package ita.support.security.validator.utils;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

public interface IUtilValidatorSecurity {

	Map<String, String> getMapRegularExpressions(String entityName);

	Map<String, String> getMessages(BindingResult bindingResult);

	boolean checkRegularExpressions(String patternString, Object object);

	void getError(Errors errors, String code, String message);

}
