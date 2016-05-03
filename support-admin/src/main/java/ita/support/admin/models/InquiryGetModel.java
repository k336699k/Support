package ita.support.admin.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InquiryGetModel {

	public Long inquiryId;
	public String description;
	public String createDate;
	public String rezalt;
	public String topic;
	public String customer;

	@JsonCreator
	public InquiryGetModel(@JsonProperty("inquiryId") Long inquiryId, @JsonProperty("description") String description,
			@JsonProperty("createDate") String createDate, @JsonProperty("rezalt") String rezalt,
			@JsonProperty("topic") String topic, @JsonProperty("customer") String customer) {
		this.inquiryId = inquiryId;
		this.description = description;
		this.createDate = createDate;
		this.rezalt = rezalt;
		this.topic = topic;
		this.customer = customer;
	}

	public InquiryGetModel() {

	}

	@Override
	public String toString() {
		return "InquiryGetModel [inquiryId=" + inquiryId + ", description=" + description + ", createDate=" + createDate
				+ ", rezalt=" + rezalt + ", topic=" + topic + ", customer=" + customer + "]";
	}

}
