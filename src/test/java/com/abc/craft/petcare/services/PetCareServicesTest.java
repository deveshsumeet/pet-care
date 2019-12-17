package com.abc.craft.petcare.services;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.abc.craft.petcare.document.Appointment;
import com.abc.craft.petcare.document.PetAnimal;
import com.abc.craft.petcare.document.Veterinarian;
import com.abc.craft.petcare.repositories.AppointmentRepository;
import com.abc.craft.petcare.repositories.PetRepository;
import com.abc.craft.petcare.repositories.VetRepository;

/**
 * The PetCareServicesTest is test class to validate all the services exposed
 * via PetCareServices.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetCareServicesTest {

	@InjectMocks
	private PetCareServicesImpl petCareServices;

	@Mock
	private VetRepository vetRepository;

	@Mock
	private PetRepository petRepository;

	@Mock
	private AppointmentRepository apptRepository;

	/* Test Case */
	@Test
	@DisplayName("Test Get All Veterinarian")
	public void testGetAllVeterinarian() {
		Veterinarian newVetObjectOne = new Veterinarian("John", "Doe");
		Veterinarian newVetObjectTwo = new Veterinarian("Jim", "Taylor");
		Veterinarian newVetObjectThree = new Veterinarian("Amit", "Kumar");
		List<Veterinarian> listVetMockObject = new ArrayList<Veterinarian>();
		listVetMockObject.add(newVetObjectOne);
		listVetMockObject.add(newVetObjectTwo);
		listVetMockObject.add(newVetObjectThree);
		Mockito.when(vetRepository.findAll()).thenReturn(listVetMockObject);
		List<Veterinarian> result = petCareServices.getAllVeterinarian();
		assertEquals(3, result.size());
	}

	/* Test Case */
	@Test
	@DisplayName("Test Get All Pet Animals")
	public void testGetAllPetAnimals() {
		PetAnimal newPetObjectOne = new PetAnimal("Harry", "Dog");
		PetAnimal newPetObjectTwo = new PetAnimal("Mini", "Cat");
		List<PetAnimal> listPetMockObject = new ArrayList<PetAnimal>();
		listPetMockObject.add(newPetObjectOne);
		listPetMockObject.add(newPetObjectTwo);
		Mockito.when(petRepository.findAll()).thenReturn(listPetMockObject);
		List<PetAnimal> result = petCareServices.getAllPetAnimal();
		assertEquals(2, result.size());
	}

	/* Test Case */
	@Test
	@DisplayName("Test Create Veterinarian")
	public void testCreateVeterinarian() {
		Veterinarian newVetMockObject = new Veterinarian("John", "Doe");
		Veterinarian newVetObject = null;
		Mockito.when(vetRepository.save(newVetMockObject)).thenReturn(newVetObject);
		Boolean result = petCareServices.createVeterinarian(newVetObject);
		assertEquals(true, result);
	}

	/* Test Case */
	@Test
	@DisplayName("Test Create Pet Animal")
	public void testCreatePetAnimal() {
		PetAnimal newPetMockObject = new PetAnimal("Tommy", "Dog");
		PetAnimal newPetObject = null;
		Mockito.when(petRepository.save(newPetMockObject)).thenReturn(newPetObject);
		Boolean result = petCareServices.createPetAnimal(newPetMockObject);
		assertEquals(true, result);
	}

	/* Test Case */
	@Test
	@DisplayName("Book Appointment")
	public void testBookAppointment() throws Exception {
		Appointment newAppMockObject = new Appointment("5df07d8778ceba204bfa870d", "5df07d5878ceba204bfa8708",
				"1606849200000");
		Mockito.when(petCareServices.bookAppointment(newAppMockObject)).thenReturn(newAppMockObject);
		assertEquals("5df07d8778ceba204bfa870d", newAppMockObject.getPetUniqueId());
	}

	/* Test Case */
	@Test
	@DisplayName("Test Cancel Not Present Appointment")
	public void testCancelNotPresentAppointment() {
		boolean result = false;
		try {
			result = petCareServices.cancelAppointment("5df07d8778ceba204bfa870d");
		} catch (Exception exceptionObject) {
			assertEquals("Appointment Already Cancelled or Not Present", exceptionObject.getMessage());
			assertEquals(false, result);
		}
	}
}
