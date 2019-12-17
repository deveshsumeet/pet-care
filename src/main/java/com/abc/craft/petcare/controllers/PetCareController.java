package com.abc.craft.petcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.abc.craft.petcare.document.Appointment;
import com.abc.craft.petcare.document.PetAnimal;
import com.abc.craft.petcare.document.Veterinarian;
import com.abc.craft.petcare.services.PetCareServices;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PetCareController class maintains all the request route and call
 * corresponding services when requests are made.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/")
public class PetCareController {

	@Autowired
	private PetCareServices petCareService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Method to handle getVeterinarian rest call.
	 * 
	 * @param none.
	 * @return List of Veterinarian
	 */
	@GetMapping(value = "getVeterinarian")
	@ResponseStatus(HttpStatus.OK)
	public List<Veterinarian> getAllVeterinarian() {
		return petCareService.getAllVeterinarian();
	}

	/**
	 * Method to handle createVeterinarian rest call.
	 * 
	 * @param Veterinarian.
	 * @return Boolean (true on Succesfull)
	 */
	@PostMapping(value = "createVeterinarian")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createVeterinarian(@Valid @RequestBody Veterinarian veterinarianObject) {
		return petCareService.createVeterinarian(veterinarianObject);
	}

	/**
	 * Method to handle getPetAnimal rest call.
	 * 
	 * @param none.
	 * @return List of PetAnimal
	 */
	@GetMapping(value = "getPetAnimal")
	@ResponseStatus(HttpStatus.OK)
	public List<PetAnimal> getAllPetAnimal() {
		return petCareService.getAllPetAnimal();
	}

	/**
	 * Method to handle createPetAnimal rest call.
	 * 
	 * @param PetAnimal.
	 * @return boolean (true on Succesfull)
	 */
	@PostMapping(value = "createPetAnimal")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createPetAnimal(@Valid @RequestBody PetAnimal pet) {
		return petCareService.createPetAnimal(pet);
	}

	/**
	 * Method to handle getPetBooking rest call.
	 * 
	 * @param petUniqueId.
	 * @return List of arraylist (Detail of each appointment for a pet)
	 */
	@GetMapping(value = "/getPetBooking/{petUniqueId}")
	@ResponseStatus(HttpStatus.OK)
	public List<ArrayList<String>> getAllPetBookings(@PathVariable String petUniqueId) {
		return petCareService.getAllPetBookings(petUniqueId);
	}

	/**
	 * Method to handle setAppointment rest call.
	 * 
	 * @param Appointment.
	 * @return ResponseEntity (Containing Appointment Object)
	 */
	@PostMapping(value = "setAppointment")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Appointment> setAppointment(@Valid @RequestBody Appointment appointment) {
		try {
			return new ResponseEntity<>(petCareService.bookAppointment(appointment), HttpStatus.CREATED);
		} catch (Exception exceptionObject) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, exceptionObject.getMessage());
		}
	}

	/**
	 * Method to handle cancelAppointment rest call.
	 * 
	 * @param bookingId.
	 * @return ResponseEntity (Containing true or false response)
	 */
	@PostMapping(value = "cancelAppointment/{bookingId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> cancelAppointment(@PathVariable String bookingId) {
		try {
			return new ResponseEntity<>(petCareService.cancelAppointment(bookingId), HttpStatus.OK);
		} catch (Exception exceptionObject) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, exceptionObject.getMessage());
		}
	}
}