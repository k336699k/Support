
package ita.support.dba.test;

import ita.support.dba.dao.IDao;
import ita.support.dba.dao.admin.ICustomerDao;
import ita.support.dba.dao.admin.IInquiryDao;
import ita.support.dba.dao.admin.ITopicDao;
import ita.support.dba.dao.admin.impl.CustomerDaoImpl;
import ita.support.dba.dao.admin.impl.InquiryDaoImpl;
import ita.support.dba.dao.admin.impl.TopicDaoImpl;
import ita.support.dba.entities.admin.Customer;
import ita.support.dba.entities.admin.Inquiry;
import ita.support.dba.entities.admin.Topic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoBeansConfiguration {

	@Bean
	public ICustomerDao customerDaoImpl() {
		return new CustomerDaoImpl();
	}
	
	@Bean
    public IDao<Customer, Long> customeryDao() {
        return new CustomerDaoImpl();
    }

	@Bean
	public IInquiryDao inquiryDaoImpl() {
		return new InquiryDaoImpl();
	}

	@Bean
    public IDao<Inquiry, Long> inquiryDao() {
        return new InquiryDaoImpl();
    }
	
	@Bean
	public ITopicDao topicDaoImpl() {
		return new TopicDaoImpl();
	}
	
	@Bean
    public IDao<Topic, Long> topicDao() {
        return new TopicDaoImpl();
    }
	

}
