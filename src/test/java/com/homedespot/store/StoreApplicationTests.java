package com.homedespot.store;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homedespot.store.controller.HomeDespotController;
import com.homedespot.store.request.HomeDespotRequest;
import com.homedespot.store.response.RentalAgreement;
import com.homedespot.store.service.HomeDespotService;

@ContextConfiguration(classes = StoreApplication.class)
@AutoConfigureMockMvc
@WebMvcTest(HomeDespotController.class)
class StoreApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Mock
	HomeDespotService homeDespotService;

	@InjectMocks
	HomeDespotController homeDespotController;

	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yy");

	@Test
	void test1() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("JAKR").checkOutDate("9/3/15")
				.rentalDayCount(5).discountPct(101).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);

		this.mockMvc.perform(post("/checkout").content(body)).andExpect(status().is4xxClientError());
	}

	@Test
	void test2() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("LADW").checkOutDate("7/2/20")
				.rentalDayCount(3).discountPct(10).build();

		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode("LADW").toolType("Ladder")
				.toolBrand("Werner").rentalDays(3).checkOutDate(DATE_FORMATTER.parse("07/02/20"))
				.dueDate(DATE_FORMATTER.parse("07/05/20")).dailyRentalCharge(1.49).chargeDays(1).preDescountCharge(1.49)
				.discountPct(10).discountAmount(0.149).finalCharge(1.341).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);
		String response = objectMapper.writeValueAsString(rentalAgreement);
		when(homeDespotService.checkoutService(homeDespotRequest)).thenReturn(rentalAgreement);
		this.mockMvc.perform(post("/checkout").contentType("application/json").content(body)).andExpect(status().isOk())
				.andExpect(content().json(response));
	}

	@Test
	void test3() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("CHNS").checkOutDate("7/2/15")
				.rentalDayCount(5).discountPct(25).build();

		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode("CHNS").toolType("Chainsaw")
				.toolBrand("Stihl").rentalDays(5).checkOutDate(DATE_FORMATTER.parse("07/02/15"))
				.dueDate(DATE_FORMATTER.parse("07/07/15")).dailyRentalCharge(1.99).chargeDays(4).preDescountCharge(7.96)
				.discountPct(25).discountAmount(1.99).finalCharge(5.97).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);
		String response = objectMapper.writeValueAsString(rentalAgreement);
		when(homeDespotService.checkoutService(homeDespotRequest)).thenReturn(rentalAgreement);
		this.mockMvc.perform(post("/checkout").contentType("application/json").content(body)).andExpect(status().isOk())
				.andExpect(content().json(response));
	}

	@Test
	void test4() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("JAKD").checkOutDate("9/3/15")
				.rentalDayCount(6).discountPct(0).build();

		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode("JAKD").toolType("Jackhammer")
				.toolBrand("DeWalt").rentalDays(6).checkOutDate(DATE_FORMATTER.parse("09/03/15"))
				.dueDate(DATE_FORMATTER.parse("09/09/15")).dailyRentalCharge(2.99).chargeDays(3).preDescountCharge(8.97)
				.discountPct(0).discountAmount(0.00).finalCharge(8.97).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);
		String response = objectMapper.writeValueAsString(rentalAgreement);
		when(homeDespotService.checkoutService(homeDespotRequest)).thenReturn(rentalAgreement);
		this.mockMvc.perform(post("/checkout").contentType("application/json").content(body)).andExpect(status().isOk())
				.andExpect(content().json(response));
	}

	@Test
	void test5() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("JAKR").checkOutDate("7/2/15")
				.rentalDayCount(9).discountPct(0).build();

		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode("JAKR").toolType("Jackhammer")
				.toolBrand("Rigid").rentalDays(9).checkOutDate(DATE_FORMATTER.parse("07/02/15"))
				.dueDate(DATE_FORMATTER.parse("07/11/15")).dailyRentalCharge(2.99).chargeDays(5)
				.preDescountCharge(14.950000000000001).discountPct(0).discountAmount(0.00).finalCharge(14.950000000000001).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);
		String response = objectMapper.writeValueAsString(rentalAgreement);
		when(homeDespotService.checkoutService(homeDespotRequest)).thenReturn(rentalAgreement);
		this.mockMvc.perform(post("/checkout").contentType("application/json").content(body)).andExpect(status().isOk())
				.andExpect(content().json(response));
	}

	@Test
	void test6() throws Exception {
		HomeDespotRequest homeDespotRequest = HomeDespotRequest.builder().toolCode("JAKR").checkOutDate("7/2/20")
				.rentalDayCount(4).discountPct(50).build();

		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode("JAKR").toolType("Jackhammer")
				.toolBrand("Rigid").rentalDays(4).checkOutDate(DATE_FORMATTER.parse("07/02/20"))
				.dueDate(DATE_FORMATTER.parse("07/06/20")).dailyRentalCharge(2.99).chargeDays(1).preDescountCharge(2.99)
				.discountPct(50).discountAmount(1.495).finalCharge(1.495).build();

		String body = objectMapper.writeValueAsString(homeDespotRequest);
		String response = objectMapper.writeValueAsString(rentalAgreement);
		when(homeDespotService.checkoutService(homeDespotRequest)).thenReturn(rentalAgreement);
		this.mockMvc.perform(post("/checkout").contentType("application/json").content(body)).andExpect(status().isOk())
				.andExpect(content().json(response));
	}

}
