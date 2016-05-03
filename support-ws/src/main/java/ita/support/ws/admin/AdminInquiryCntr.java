package ita.support.ws.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ita.support.admin.IAdminFacade;
import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.validator.CreateInquiryValidator;
import ita.support.admin.validator.utils.IUtilClassValidator;
import ita.support.utils.exceptions.FtException;

@RestController
@RequestMapping("admin")
public class AdminInquiryCntr {

	private Logger logger = LoggerFactory.getLogger(AdminInquiryCntr.class.getName());

	@Autowired
	private IAdminFacade adminFacade;

	@Autowired
	private IUtilClassValidator util;

	@Autowired
	CreateInquiryValidator inquiryValidator;

	@RequestMapping(path = "/customers/{customerName}/inquiries", method = RequestMethod.GET)
	public ResponseEntity<?> getInquiriesByCustomers(@PathVariable("customerName") String customerName) {
		List<InquiryGetModel> inquiryModelList = null;

		try {
			inquiryModelList = adminFacade.getInquiriesByCustomer(customerName);
			if (inquiryModelList == null) {
				return new ResponseEntity<List<InquiryGetModel>>(inquiryModelList, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<InquiryGetModel>>(inquiryModelList, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error! Exception:" + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

	@RequestMapping(path = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.GET)
	public ResponseEntity<?> getInquiryByCustomers(@PathVariable("customerName") String customerName,
			@PathVariable("inquiryId") Long inquiryId) {
		InquiryGetModel inquiryModel = null;

		try {
			inquiryModel = adminFacade.getInquiryByCustomer(customerName, inquiryId);
			if (inquiryModel == null) {
				return new ResponseEntity<InquiryGetModel>(inquiryModel, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<InquiryGetModel>(inquiryModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error! Exception:" + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

	@RequestMapping(path = "/customers/{customerName}/inquiries", method = RequestMethod.POST)
	public ResponseEntity<?> createInquiry(@RequestBody InquiryPostModel inquiryPostModel,
			@PathVariable("customerName") String customerName, BindingResult bindingResult) {

		inquiryValidator.validate(inquiryPostModel, bindingResult);

		if (!bindingResult.hasErrors()) {
			if (adminFacade.createInquiry(inquiryPostModel, customerName)) {
				logger.debug("Inquiry created " + customerName);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				logger.debug("Inquiry did not create  " + customerName);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
			return new ResponseEntity<Map<String, String>>(util.getMessages(bindingResult), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(path = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateInquiry(@RequestBody InquiryPostModel inquiryPostModel,
			@PathVariable("customerName") String customerName, @PathVariable("inquiryId") Long inquiryId, BindingResult bindingResult) {

		inquiryValidator.validate(inquiryPostModel, bindingResult);

		if (!bindingResult.hasErrors()) {
			if (adminFacade.updateInquiry(inquiryPostModel, customerName, inquiryId)) {
				logger.debug("Inquiry updated " + customerName);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				logger.debug("Inquiry did not updat  " + customerName);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
			return new ResponseEntity<Map<String, String>>(util.getMessages(bindingResult), HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInquiry(@PathVariable("customerName") String customerName, @PathVariable("inquiryId") Long inquiryId) throws Exception {
		if (inquiryId == null || customerName == null) {
			logger.debug("[/customers/{customerName}/inquiries/{inquiryId}] with id:'null' in request!");
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		logger.debug("Delete Inquiry with id: " + inquiryId);
		try {
			boolean rezult = adminFacade.deleteInquiry(customerName, inquiryId);
			return new ResponseEntity<Boolean>(rezult, HttpStatus.OK);
		} catch (FtException e) { 
			logger.debug("The City can not be get with id: " + inquiryId);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
}
