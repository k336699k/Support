package ita.support.admin.utils;

import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.models.TopicGetModel;
import ita.support.dba.entities.admin.Customer;
import ita.support.dba.entities.admin.Inquiry;
import ita.support.dba.entities.admin.Topic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AdminModelsTransformer {

	public TopicGetModel buildTopicModel(Topic topic) {
		Long idTopic = topic.getTopicId();
		String name = topic.getName();

		TopicGetModel topicGetModel = new TopicGetModel(idTopic, name);

		return topicGetModel;
	}

	public List<TopicGetModel> buildListTopicModel(List<Topic> topics) {
		List<TopicGetModel> topicModelList = new ArrayList<TopicGetModel>();

		for (int i = 0; i < topics.size(); i++) {
			topicModelList.add(buildTopicModel(topics.get(i)));
		}
		return topicModelList;
	}

	public InquiryGetModel buildInquiryModel(Inquiry inquiry) {
		Long inquiryId = inquiry.getInquiryId();
		String description = inquiry.getDescription();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String createDate = formatter.format(inquiry.getCreateDate());
		String rezalt = "";
		if (inquiry.getRezalt() == 1) {
			rezalt = "in processing";
		} else if (inquiry.getRezalt() == 0) {

			rezalt = "completed";
		}
		String topic = inquiry.getTopic().getName();
		String customer = inquiry.getCustomer().getName();
		InquiryGetModel inquiryGetModel = new InquiryGetModel(inquiryId, description, createDate, rezalt, topic,
				customer);
		return inquiryGetModel;

	}

	public List<InquiryGetModel> buildListInquiryModel(List<Inquiry> inquiry, String name) {
		List<InquiryGetModel> inquiryGetModel = new ArrayList<InquiryGetModel>();

		for (int i = 0; i < inquiry.size(); i++) {
			if (inquiry.get(i).getCustomer().getName().equals(name)) {
				inquiryGetModel.add(buildInquiryModel(inquiry.get(i)));
			}
		}
		return inquiryGetModel;
	}
	
	public Inquiry buildInquiry(InquiryPostModel inquiryPostModel, Customer customer, Topic topic) throws Exception{
		Inquiry inquiry = new Inquiry();
		inquiry.setDescription(inquiryPostModel.description);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date createDate = formatter.parse(inquiryPostModel.createDate);
		inquiry.setCreateDate(createDate);
		String rezalt = inquiryPostModel.rezalt;
		if (rezalt.equals("in processing")) {
			inquiry.setRezalt(1);
		} else
			if (rezalt.equals("completed")) {
				inquiry.setRezalt(0);
			}
		inquiry.setTopic(topic);
		inquiry.setCustomer(customer);
		return inquiry;
	}
	
	public Inquiry udateInquiry(InquiryPostModel inquiryPostModel, Customer customer, Topic topic, Inquiry inquiry) throws Exception{
		inquiry.setDescription(inquiryPostModel.description);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date createDate = formatter.parse(inquiryPostModel.createDate);
		inquiry.setCreateDate(createDate);
		String rezalt = inquiryPostModel.rezalt;
		if (rezalt.equals("in processing")) {
			inquiry.setRezalt(1);
		} else
			if (rezalt.equals("completed")) {
				inquiry.setRezalt(0);
			}
		inquiry.setTopic(topic);
		inquiry.setCustomer(customer);
		return inquiry;
	}
}
