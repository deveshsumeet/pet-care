package com.abc.craft.petcare.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The PetAnimal class is to map the basic structure of a document in mongoDB
 * petAnimal collection. This is the model that identifies the data structure
 * stored in the database to Spring Boot.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Document(collection = "petAnimal")
public class PetAnimal {

	@Id
	private ObjectId petUniqueId;
	private String petName;
	private String petKind;

	public String getPetUniqueId() {
		return petUniqueId.toHexString();
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetKind() {
		return petKind;
	}

	public void setPetKind(String petKind) {
		this.petKind = petKind;
	}

	public PetAnimal() {
	}

	public PetAnimal(String petName, String petKind) {
		this.petName = petName;
		this.petKind = petKind;
	}
}
