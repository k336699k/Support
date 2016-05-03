package ita.support.admin;

import java.util.List;

import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.models.TopicGetModel;
import ita.support.utils.exceptions.FtException;

public interface IAdminFacade {

	/**
	 * added by Konstantin Shaplyko on 01.05.2016
	 */
	List<TopicGetModel> getAllTopics();
	
	/**
	 * added by Konstantin Shaplyko on 01.05.2016
	 */
	public List<InquiryGetModel> getInquiriesByCustomer(String name);
	
	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	InquiryGetModel getInquiryByCustomer(String name, Long id);
	
	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	boolean createInquiry(InquiryPostModel inquiryPostModel, String name);
	
	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	boolean updateInquiry(InquiryPostModel inquiryPostModel, String name, Long id);
	
	/**
	 * added by Konstantin Shaplyko on 02.05.2016
	 */
	boolean deleteInquiry(String name,Long id) throws FtException;

}
