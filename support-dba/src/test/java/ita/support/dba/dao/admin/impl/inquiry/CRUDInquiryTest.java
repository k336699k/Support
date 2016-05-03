package ita.support.dba.dao.admin.impl.inquiry;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;
import java.util.List;
import ita.support.dba.DaoFtException;
import ita.support.dba.dao.admin.IInquiryDao;
import ita.support.dba.entities.admin.Customer;
import ita.support.dba.entities.admin.Inquiry;
import ita.support.dba.entities.admin.Topic;
import ita.support.dba.test.DaoBeansConfiguration;
import ita.support.dba.test.HibernateConfigurationTest;

@DataSet
@Transactional
public class CRUDInquiryTest extends UnitilsJUnit4 {
	private Logger log = LoggerFactory.getLogger(CRUDInquiryTest.class);

	@SpringApplicationContext
	public ConfigurableApplicationContext createApplicationContext() {
		return new AnnotationConfigApplicationContext(HibernateConfigurationTest.class, DaoBeansConfiguration.class);
	}

	@SpringBeanByName
	IInquiryDao inquiryDao;

	@Before
	public void before() {

	}

	@Test
	@Rollback
	public void getInquiryTest() throws DaoFtException {
		Inquiry inquiry = inquiryDao.get(1L);
		assertTrue(inquiry.getInquiryId().equals(1L));
		assertTrue(inquiry.getCreateDate().getTime() == Date.valueOf("2016-05-03").getTime());
		assertTrue(inquiry.getDescription().equals("10"));
		assertTrue(inquiry.getRezalt() == 1);
		assertTrue(inquiry.getCustomer().getCustmerId().equals(1L));
		assertTrue(inquiry.getTopic().getTopicId().equals(1L));

	}

	@Test
	@Rollback
	public void addInquiryTest() throws DaoFtException {
		Inquiry inquiry = new Inquiry();
		inquiry.setCreateDate(Date.valueOf("2016-04-02"));
		inquiry.setDescription("10");
		inquiry.setRezalt(1);

		Customer customer = new Customer();
		customer.setCustmerId(1L);
		inquiry.setCustomer(customer);
		Topic topic = new Topic();
		topic.setTopicId(1L);
		inquiry.setTopic(topic);
		Long inquiryId = (Long) inquiryDao.add(inquiry);

		Inquiry inquiryRezalt = inquiryDao.get(inquiryId);
		assertTrue(inquiryRezalt.getInquiryId().equals(inquiryId));
		assertTrue(inquiryRezalt.getCreateDate().getTime() == Date.valueOf("2016-04-02").getTime());
		assertTrue(inquiryRezalt.getDescription().equals("10"));
		assertTrue(inquiryRezalt.getRezalt() == 1);
		assertTrue(inquiryRezalt.getCustomer().getCustmerId().equals(1L));
		assertTrue(inquiryRezalt.getTopic().getTopicId().equals(1L));

	}

	@Test
	@Rollback
	public void getAllInquiryTest() throws DaoFtException {

		List<Inquiry> result = inquiryDao.getAll();
		assertTrue(result.size() == 3);
	}

	@Test
	@Rollback

	public void updateInquiryTest() throws DaoFtException {
		Inquiry inquiryOld = inquiryDao.get(3L);
		inquiryOld.setCreateDate(Date.valueOf("2016-04-02"));
		inquiryOld.setDescription("10");
		inquiryOld.setRezalt(1);

		Customer customer = new Customer();
		customer.setCustmerId(1L);
		inquiryOld.setCustomer(customer);
		Topic topicNew = new Topic();
		topicNew.setTopicId(3L);

		inquiryDao.update(inquiryOld);

		Inquiry inquiryRezalt2 = inquiryDao.get(3L);
		assertTrue(inquiryRezalt2.getInquiryId().equals(3L));
		assertTrue(inquiryRezalt2.getCreateDate().getTime() == Date.valueOf("2016-04-02").getTime());
		assertTrue(inquiryRezalt2.getDescription().equals("10"));
		assertTrue(inquiryRezalt2.getRezalt() == 1);
		assertTrue(inquiryRezalt2.getCustomer().getCustmerId().equals(1L));

	}
}
