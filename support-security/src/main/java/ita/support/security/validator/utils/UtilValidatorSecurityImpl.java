package ita.support.security.validator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Component
@PropertySource(value = { "classpath:/validator/company/validator-messages.properties",
		"classpath:/validator/company/regex.properties" })
public class UtilValidatorSecurityImpl implements IUtilValidatorSecurity {

	private Logger log = LoggerFactory.getLogger(UtilValidatorSecurityImpl.class);

	@Autowired
	private Environment environment;

	@Override
	public Map<String, String> getMapRegularExpressions(String entityName) {

		Map<String, String> listRegex = new HashMap<String, String>();

		try (InputStream inputStream = getClass().getResourceAsStream("/validator/company/regex.properties")) {
			Properties prop = new Properties();
			prop.load(inputStream);
			for (String property : prop.stringPropertyNames()) {
				if (property.substring(0, property.indexOf('.')).trim().equals(entityName)) {
					listRegex.put(property, prop.getProperty(property));
				}
			}
		} catch (IOException e) {
			log.error("Error: IOExeption");
		}
		return listRegex;
	}

	@Override
	public Map<String, String> getMessages(BindingResult bindingResult) {
		List<ObjectError> listErrors = bindingResult.getAllErrors();
		Map<String, String> mapErrors = new HashMap<>();
		for (int i = 0; i < listErrors.size(); i++) {
			mapErrors.put(listErrors.get(i).getCode(), listErrors.get(i).getDefaultMessage());
		}
		return mapErrors;
	}

	@Override
	public boolean checkRegularExpressions(String patternString, Object object) {

		String matcherString = object.toString();

		Pattern pattern = Pattern.compile(environment.getRequiredProperty(patternString));
		Matcher matcher = pattern.matcher(matcherString);
		return matcher.matches();
	}

	@Override
	public void getError(Errors errors, String code, String message) {
		errors.reject(code, environment.getRequiredProperty(message));
	}

}
