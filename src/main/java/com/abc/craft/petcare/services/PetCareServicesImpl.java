package com.abc.craft.petcare.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abc.craft.petcare.document.Appointment;
import com.abc.craft.petcare.document.PetAnimal;
import com.abc.craft.petcare.document.Veterinarian;
import com.abc.craft.petcare.repositories.AppointmentRepository;
import com.abc.craft.petcare.repositories.PetRepository;
import com.abc.craft.petcare.repositories.VetRepository;

/**
 * The PetCareServicesImpl is a service class implementing PetCareServices. All
 * services mentioned in PetCareServices is implemented here and this class
 * takes care of business logic and validations and data save.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Service
public class PetCareServicesImpl implements PetCareServices {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private VetRepository vetRepo;

	@Autowired
	private PetRepository petRepo;

	/**
	 * Service method implementation to book appointment
	 * 
	 * @param Appointment
	 * @return Appointment
	 */
	@Override
	public Appointment bookAppointment(Appointment appointmentObject) throws Exception {
		String bookingTime = appointmentObject.getBookingTime();
		Date bookingDateTime = new Date(Long.parseLong(bookingTime));
		Calendar cal = Calendar.getInstance();
		cal.setTime(bookingDateTime);
		int bookingHour = cal.get(Calendar.HOUR_OF_DAY);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String vetUniqueId = appointmentObject.getVetUniqueId();
		/* Logic to check if vet is already booked at the time */
		Set<Appointment> settOfVetAppt = appointmentRepository.findByVetUniqueId(vetUniqueId);
		boolean isVetBooked = settOfVetAppt.stream().filter(object -> object.getBookingTime().equals(bookingTime))
				.findFirst().isPresent();
		/* Logic to check appointment is being made during working hours */
		if (bookingDateTime.before(new Date())) {
			throw new Exception("Select Any Date and Time After Current Time");
		} else if (bookingHour < 8 || bookingHour > 17 || dayOfWeek == 1 || dayOfWeek == 7) {
			throw new Exception("Outside Office Hours");
		} else if (isVetBooked) {
			throw new Exception("Veterinarian already booked at this time");
		} else {
			Appointment newAppointment = new Appointment(appointmentObject.getPetUniqueId(),
					appointmentObject.getVetUniqueId(), appointmentObject.getBookingTime());
			return appointmentRepository.save(newAppointment);
		}
	}

	/**
	 * Service method implementation to cancel appointment
	 * 
	 * @param bookingId
	 * @return boolean
	 */
	@Override
	public boolean cancelAppointment(String bookingId) throws Exception {
		Optional<Appointment> appointmentObject = appointmentRepository.findById(bookingId);
		if (!appointmentObject.isPresent()) {
			throw new Exception("Appointment Already Cancelled or Not Present");
		} else if (new Date(Long.parseLong(appointmentObject.get().getBookingTime())).before(new Date())) {
			throw new Exception("Appointment Time Already Passed");
		} else {
			appointmentRepository.deleteById(bookingId);
			return true;
		}
	}

	/**
	 * Service method implementation to get all bookings for a pet
	 * 
	 * @param petUniqueId
	 * @return List of Array List (Containing detail of all appointments)
	 */
	@Override
	public List<ArrayList<String>> getAllPetBookings(String petUniqueId) {
		List<ArrayList<String>> listOfBookingObject = new ArrayList<ArrayList<String>>();
		Set<Appointment> setOfPetAppt = appointmentRepository.findByPetUniqueId(petUniqueId);
		for (Appointment eachAppt : setOfPetAppt) {
			ArrayList<String> eachBookingObject = new ArrayList<String>();
			eachBookingObject.add(eachAppt.getBookingId());
			eachBookingObject.add(vetRepo.findById(eachAppt.getVetUniqueId()).get().getFirstName() + " "
					+ vetRepo.findById(eachAppt.getVetUniqueId()).get().getLastName());
			eachBookingObject.add((new Date(Long.parseLong(eachAppt.getBookingTime()))).toString());
			listOfBookingObject.add(eachBookingObject);
		}
		return listOfBookingObject;
	}

	/**
	 * Service method implementation to create a vet in system
	 * 
	 * @param Veterinarian
	 * @return Boolean
	 */
	@Override
	public boolean createVeterinarian(Veterinarian veterinarianObject) {
		vetRepo.save(veterinarianObject);
		return true;
	}

	/**
	 * Service method implementation to get all vet present in system
	 * 
	 * @param None
	 * @return List of Veterinarian
	 */
	@Override
	public List<Veterinarian> getAllVeterinarian() {
		return vetRepo.findAll();
	}

	/**
	 * Service method implementation to create a pet in system
	 * 
	 * @param PetAnimal
	 * @return Boolean
	 */
	@Override
	public boolean createPetAnimal(PetAnimal petAnimalObject) {
		petRepo.save(petAnimalObject);
		return true;
	}

	/**
	 * Service method implementation to get all pets present in system
	 * 
	 * @param None
	 * @return List of PetAnimal
	 */
	@Override
	public List<PetAnimal> getAllPetAnimal() {
		return petRepo.findAll();
	}
}
