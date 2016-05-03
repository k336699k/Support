package ita.support.admin.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicGetModel {

	public Long topicId;
	public String name;

	@JsonCreator
	public TopicGetModel(@JsonProperty("topicId") Long topicId, @JsonProperty("name") String name) {
		this.topicId = topicId;
		this.name = name;

	}

	public TopicGetModel() {

	}

	@Override
	public String toString() {
		return "TopicGetModel [topicId=" + topicId + ", name=" + name + "]";
	}

}
