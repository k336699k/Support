package ita.support.admin.validator.utils;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

public interface IUtilClassValidator {

	Map<String, String> getMapRegularExpressions(String entityName);

	Map<String, String> getMessages(BindingResult bindingResult);

	boolean checkRegularExpressions(String patternString, Object object);

	void getError(Errors errors, String code, String message);

}
