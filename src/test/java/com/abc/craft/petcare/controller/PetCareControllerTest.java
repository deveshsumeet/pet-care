package com.abc.craft.petcare.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.abc.craft.petcare.PetCareApplication;

/**
 * The PetCareController test class to test all controllers and routing of
 * request.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = PetCareApplication.class)
public class PetCareControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	/* Test Case */
	@Test
	@DisplayName("Test getVeterinarian End Point ")
	public void testAllVetList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getVeterinarian").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	/* Test Case */
	@Test
	@DisplayName("Test getPetAnimal End Point ")
	public void testAllPetList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getPetAnimal").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}
