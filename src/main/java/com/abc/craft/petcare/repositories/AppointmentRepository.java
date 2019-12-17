package com.abc.craft.petcare.repositories;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.abc.craft.petcare.document.Appointment;

/**
 * The AppointmentRepository interface is to create the connector between the
 * Appointment data model and veterinarian collection present in MongoDB. This
 * extends MongoRepository which already contains generic methods like save,
 * delete and helps to perform find queries.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

	/**
	 * Method to find set of all appointments for a pet
	 * 
	 * @param petUniqueId.
	 * @return Set of all appointments.
	 */
	Set<Appointment> findByPetUniqueId(String petUniqueId);

	/**
	 * Method to find set of all appointments for a veterinarian
	 * 
	 * @param vetUniqueId.
	 * @return Set of all appointments.
	 */
	Set<Appointment> findByVetUniqueId(String vetUniqueId);

	/**
	 * Method to find an appointment at a particular time
	 * 
	 * @param bookingTime.
	 * @return Appointment object.
	 */
	Appointment findByBookingTime(String bookingTime);
}
