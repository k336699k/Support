/*
package ita.support.admin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ita.support.admin.models.CityCreateModel;
import ita.support.admin.validator.utils.IUtilClassValidator;

@Component
public class CreateCityValidator implements Validator {

	@Autowired
	private IUtilClassValidator util;

	@Override
	public boolean supports(Class<?> clazz) {

		return CityCreateModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		CityCreateModel city = (CityCreateModel) object;
		// City.name
		if (city.name == null || city.name.equals("")) {
			util.getError(errors, "city.name", "city.name.empty");
		} else
			if (!util.checkRegularExpressions("city.name.check_first_character", city.name)) {
				util.getError(errors, "city.name", "city.name.error_characters");
			} else
				if (!util.checkRegularExpressions("city.name.check_characters", city.name)) {
					util.getError(errors, "city.name", "city.name.length");
				}

		// City.region
		if (city.region == null || city.region.equals("")) {
			util.getError(errors, "city.region", "city.region.empty");
		} else
			if (!util.checkRegularExpressions("city.region.check_first_character", city.region)) {
				util.getError(errors, "city.region", "city.region.error_characters");
			} else
				if (!util.checkRegularExpressions("city.region.check_characters", city.region)) {
					util.getError(errors, "city.region", "city.region.length");
				}

	}

}
*/