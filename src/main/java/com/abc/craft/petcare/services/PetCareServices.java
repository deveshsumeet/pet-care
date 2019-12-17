package com.abc.craft.petcare.services;

import java.util.ArrayList;
import java.util.List;
import com.abc.craft.petcare.document.Appointment;
import com.abc.craft.petcare.document.PetAnimal;
import com.abc.craft.petcare.document.Veterinarian;

/**
 * The PetCareServices interface is to list down all the services exposed by
 * pet-care application.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
public interface PetCareServices {

	/**
	 * Method to create veterinarian
	 * 
	 * @param Veterinarian
	 * @return boolean (true on succesfull)
	 */
	public boolean createVeterinarian(Veterinarian veterinarianObject);

	/**
	 * Method to get all veterinarian present in system
	 * 
	 * @param none
	 * @return List of all veterinarians
	 */
	public List<Veterinarian> getAllVeterinarian();

	/**
	 * Method to create pet animal
	 * 
	 * @param petAnimalObject
	 * @return boolean (true on succesfull)
	 */
	public boolean createPetAnimal(PetAnimal petAnimalObject);

	/**
	 * Method to get all pet animal present in system
	 * 
	 * @param none
	 * @return List of all pet animals
	 */
	public List<PetAnimal> getAllPetAnimal();

	/**
	 * Method to find list of all bookings for a pet
	 * 
	 * @param petUniqueId
	 * @return List of all appointments with detail in a list
	 */
	public List<ArrayList<String>> getAllPetBookings(String petUniqueId);

	/**
	 * Method to book an appointment
	 * 
	 * @param appointmentObject
	 * @throws Exception
	 * @return Appointment
	 */
	public Appointment bookAppointment(Appointment appointmentObject) throws Exception;

	/**
	 * Method to cancel an appointment
	 * 
	 * @param bookingId
	 * @throws Exception
	 * @return boolean (true on succesfull)
	 */
	public boolean cancelAppointment(String bookingId) throws Exception;

}
