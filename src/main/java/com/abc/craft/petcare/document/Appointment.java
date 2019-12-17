package com.abc.craft.petcare.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Appointment class is to map the basic structure of a document in mongoDB
 * appointment collection. This is the model that identifies the data structure
 * stored in the database to Spring Boot.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Document(collection = "appointment")
public class Appointment {

	@Id
	private ObjectId bookingId;
	private String petUniqueId;
	private String vetUniqueId;
	private String bookingTime;

	public String getBookingId() {
		return bookingId.toHexString();
	}

	public String getPetUniqueId() {
		return petUniqueId;
	}

	public void setPetUniqueId(String petUniqueId) {
		this.petUniqueId = petUniqueId;
	}

	public String getVetUniqueId() {
		return vetUniqueId;
	}

	public void setVetUniqueId(String vetUniqueId) {
		this.vetUniqueId = vetUniqueId;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Appointment() {
	}

	public Appointment(String petUniqueId, String vetUniqueId, String bookingTime) {
		this.petUniqueId = petUniqueId;
		this.vetUniqueId = vetUniqueId;
		this.bookingTime = bookingTime;
	}

}
