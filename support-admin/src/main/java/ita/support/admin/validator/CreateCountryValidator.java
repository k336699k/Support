/*
package ita.support.admin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ita.support.admin.models.CountryCreateModel;
import ita.support.admin.validator.utils.IUtilClassValidator;

@Component
public class CreateCountryValidator implements Validator {

	@Autowired
	private IUtilClassValidator util;

	@Override
	public boolean supports(Class<?> clazz) {
		return CountryCreateModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		CountryCreateModel country = (CountryCreateModel) object;
		// Country.name
		if (country.name == null || country.name.equals("")) {
			util.getError(errors, "country.name", "contry.name.empty");
		} else
			if (!util.checkRegularExpressions("country.name.check_first_character", country.name)) {
				util.getError(errors, "country.name", "contry.name.error_characters");
			} else
				if (!util.checkRegularExpressions("country.name.check_characters", country.name)) {
					util.getError(errors, "country.name", "contry.name.length");
				}

		// Country.codeCountry
		if (country.codeCountry == null || country.codeCountry.equals("")) {
			util.getError(errors, "country.codeCountry", "contry.codeCountry.empty");
		} else
			if (!util.checkRegularExpressions("country.codeCountry.check_characters", country.codeCountry)) {
				util.getError(errors, "country.codeCountry", "contry.codeCountry.length");
			}

	}

}
*/