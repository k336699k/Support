package ita.support.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ita.support.admin.managers.IAdminInquiryMng;
import ita.support.admin.managers.IAdminTopicMng;
import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.models.TopicGetModel;
import ita.support.utils.exceptions.FtException;

@Component
public class AdminFacadeImpl implements IAdminFacade {

	@Autowired
	IAdminTopicMng topicMng;

	@Autowired
	IAdminInquiryMng adminInquiryMng;

	/**
	 * Created by Konstantin Shaplyko, 01.05.2016
	 */
	@Override
	public List<TopicGetModel> getAllTopics() {
		return topicMng.getAllTopics();
	}

	/**
	 * Created by Konstantin Shaplyko, 01.05.2016
	 */
	@Override
	public List<InquiryGetModel> getInquiriesByCustomer(String name) {
		return adminInquiryMng.getInquiriesByCustomer(name);
	}

	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	@Override
	public InquiryGetModel getInquiryByCustomer(String name, Long id) {
		return adminInquiryMng.getInquiryByCustomer(name, id);
	}

	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	@Override
	public boolean createInquiry(InquiryPostModel inquiryPostModel, String name) {
		return adminInquiryMng.createInquiry(inquiryPostModel, name);
	}

	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	@Override
	public boolean updateInquiry(InquiryPostModel inquiryPostModel, String name, Long id) {
		return adminInquiryMng.updateInquiry(inquiryPostModel, name, id);
	}

	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	@Override
	public boolean deleteInquiry(String name, Long id) throws FtException {
		return adminInquiryMng.deleteInquiry(name, id);
	}
}
