package ita.support.admin.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InquiryPostModel {

	public String description;
	public String createDate;
	public String rezalt;
	public String topic;

	@JsonCreator
	public InquiryPostModel(@JsonProperty("description") String description,
			@JsonProperty("createDate") String createDate, @JsonProperty("rezalt") String rezalt,
			@JsonProperty("topic") String topic) {
		this.description = description;
		this.createDate = createDate;
		this.rezalt = rezalt;
		this.topic = topic;
	}

	public InquiryPostModel() {

	}

	@Override
	public String toString() {
		return "InquiryGetModel [description=" + description + ", createDate=" + createDate + ", rezalt=" + rezalt
				+ ", topic=" + topic + "]";
	}

}
