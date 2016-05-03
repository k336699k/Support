package ita.support.admin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.validator.utils.IUtilClassValidator;

@Component
public class CreateInquiryValidator implements Validator {

	@Autowired
	private IUtilClassValidator util;

	@Override
	public boolean supports(Class<?> clazz) {

		return InquiryPostModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		InquiryPostModel inquiry = (InquiryPostModel) object;

		if (inquiry.description == null || inquiry.description.equals("")) {
			util.getError(errors, "inquiry.description", "inquiry.description.empty");
		} else if (inquiry.description.length() < 6 || inquiry.description.length() > 25) {
			util.getError(errors, "inquiry.description", "inquiry.description.length");
		} else if (!util.checkRegularExpressions("inquiry.description.check", inquiry.description)) {
			util.getError(errors, "inquiry.description", "inquiry.description.error_characters");

		}

		if (inquiry.createDate == null || inquiry.createDate.equals("")) {
			util.getError(errors, "inquiry.createDate", "inquiry.createDate.empty");
		} else if (!util.checkRegularExpressions("inquiry.createDate.check_number", inquiry.createDate)) {
			util.getError(errors, "inquiry.createDate", "inquiry.description.error_characters");

		}

		// if (inquiry.rezalt.equals("completed") || inquiry.rezalt.equals("in
		// processing")) {
		// util.getError(errors, "inquiry.rezalt", "inquiry.rezalt.empty");
		// }

		if (!util.checkRegularExpressions("inquiry.rezalt.sheck_rezalt", inquiry.rezalt)) {
			util.getError(errors, "inquiry.rezalt", "inquiry.rezalt.empty");
		}

		if (inquiry.topic == null || inquiry.topic.equals("")) {
			util.getError(errors, "inquiry.topic", "inquiry.topic.empty");
		}

	}
}
