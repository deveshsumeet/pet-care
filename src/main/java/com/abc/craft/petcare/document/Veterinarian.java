package com.abc.craft.petcare.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Veterinarian class is to map the basic structure of a document in mongoDB
 * veterinarian collection. This is the model that identifies the data structure
 * stored in the database to Spring Boot.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Document(collection = "veterinarian")
public class Veterinarian {

	@Id
	private ObjectId vetUniqueId;
	private String firstName;
	private String lastName;

	public String getVetUniqueId() {
		return vetUniqueId.toHexString();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Veterinarian() {
	}

	public Veterinarian(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
