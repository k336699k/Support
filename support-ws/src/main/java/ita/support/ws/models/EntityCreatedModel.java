package ita.support.ws.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityCreatedModel {
	public final long id;

	@JsonCreator
	public EntityCreatedModel(@JsonProperty("id") long id) {
		this.id = id;
	}
}
