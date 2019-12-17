package com.abc.craft.petcare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.abc.craft.petcare.controllers.PetCareController;

/**
 * The PetCareApplication Tests class.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetCareApplicationTests {

	@Autowired
	PetCareController petCareController;

	@Test
	public void contextLoads() {
		assertNotNull(petCareController);
	}

}