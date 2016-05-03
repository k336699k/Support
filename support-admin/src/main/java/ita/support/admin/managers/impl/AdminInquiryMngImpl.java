package ita.support.admin.managers.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ita.support.admin.managers.IAdminInquiryMng;
import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.utils.AdminModelsTransformer;
import ita.support.dba.DaoFtException;
import ita.support.dba.dao.admin.ICustomerDao;
import ita.support.dba.dao.admin.IInquiryDao;
import ita.support.dba.dao.admin.ITopicDao;
import ita.support.dba.entities.admin.Customer;
import ita.support.dba.entities.admin.Inquiry;
import ita.support.dba.entities.admin.Topic;
import ita.support.utils.exceptions.FtException;

@Service
@Transactional
public class AdminInquiryMngImpl implements IAdminInquiryMng {

	private Logger log = LoggerFactory.getLogger(AdminInquiryMngImpl.class);

	@Autowired
	private IInquiryDao inquiryDAO;

	@Autowired
	private ICustomerDao customerDAO;

	@Autowired
	private ITopicDao topicDAO;

	@Autowired
	private AdminModelsTransformer modelsTransformer;

	@Override
	public List<InquiryGetModel> getInquiriesByCustomer(String name) {
		log.debug("lunch metod getInquiriesByCustomer()");
		List<Inquiry> inquiries = null;
		try {
			inquiries = inquiryDAO.getAll();
		} catch (DaoFtException e) {
			throw new FtException("Cannot get Inquiries from DB", e);
		}
		List<InquiryGetModel> inquiryModelList = new ArrayList<InquiryGetModel>();
		inquiryModelList = modelsTransformer.buildListInquiryModel(inquiries, name);

		return inquiryModelList;
	}

	@Override
	public InquiryGetModel getInquiryByCustomer(String name, Long id) {
		log.debug("lunch metod getInquiryByCustomer()");
		Inquiry inquiry = null;
		try {
			inquiry = inquiryDAO.get(id);
		} catch (DaoFtException e) {
			throw new FtException("Cannot get Inquiry from DB", e);
		}
		InquiryGetModel inquiryGetModel = null;
		if (inquiry.getCustomer().getName().equals(name)) {
			inquiryGetModel = modelsTransformer.buildInquiryModel(inquiry);
		}
		return inquiryGetModel;
	}

	@Override
	public boolean createInquiry(InquiryPostModel inquiryPostModel, String name) {
		Inquiry inquiry = new Inquiry();
		boolean result = false;
		Customer customer = new Customer();
		customer = customerDAO.getCustomerByName(name);
		Topic topic = new Topic();
		topic = topicDAO.getTopicByName(inquiryPostModel.topic);

		try {
			inquiry = modelsTransformer.buildInquiry(inquiryPostModel, customer, topic);
			inquiryDAO.add(inquiry);
			result = true;

		} catch (Exception e) {
			log.error("Error! Exception:" + e.getLocalizedMessage());
		}

		return result;
	}

	@Override
	public boolean updateInquiry(InquiryPostModel inquiryPostModel, String name, Long id) {
		boolean result = false;
		Inquiry inquiry = new Inquiry();
		Inquiry inquiryUpdate = new Inquiry();
		inquiry = inquiryDAO.get(id);
		Customer customer = new Customer();
		customer = customerDAO.getCustomerByName(name);
		Topic topic = new Topic();
		topic = topicDAO.getTopicByName(inquiryPostModel.topic);
		try {
			inquiryUpdate = modelsTransformer.udateInquiry(inquiryPostModel, customer, topic, inquiry);
			inquiryDAO.update(inquiryUpdate);
			result = true;

		} catch (Exception e) {
			log.error("Error! Exception:" + e.getLocalizedMessage());
		}

		return result;
	}

	@Override
	public boolean deleteInquiry(String name, Long id) throws FtException {
		boolean result = false;
		Inquiry inquiry = new Inquiry();
		try {
			inquiry = inquiryDAO.get(id);
			if (inquiry == null) {
				throw new FtException("Inquiry with this Id does't exist in Database.");
			}
		} catch (DaoFtException e1) {
			log.error("Error! DAO Exception:" + e1.getLocalizedMessage());
		}

		int rezaltForDelete = inquiry.getRezalt();
		if (rezaltForDelete == 1) {
			throw new FtException("Inquiry is not processed. You can't delete this Inquiry");
		}

		String nameCustomer = inquiry.getCustomer().getName();
		if (!nameCustomer.equals(name)) {
			throw new FtException("Customer name is not correct. You can't delete this Inquiry");
		}
		
		try {
			inquiry.setCustomer(null);
			inquiry.setTopic(null);
			inquiryDAO.delete(inquiry);
			result = true;
		} catch (DaoFtException e4) {
			log.error("Error! DAO Exception:" + e4.getLocalizedMessage());
		}

		return result;
	}

}
