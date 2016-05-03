package ita.support.admin.managers;

import java.util.List;

import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.utils.exceptions.FtException;

public interface IAdminInquiryMng {
	
	List<InquiryGetModel> getInquiriesByCustomer(String name);
	
	InquiryGetModel getInquiryByCustomer(String name, Long id);
	
	boolean createInquiry(InquiryPostModel inquiryPostModel, String name);
	
	boolean updateInquiry(InquiryPostModel inquiryPostModel, String name, Long id);
	
	boolean deleteInquiry(String name,Long id) throws FtException;

}
