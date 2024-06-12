package com.homedespot.store.controller;

import java.text.ParseException;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedespot.store.request.HomeDespotRequest;
import com.homedespot.store.response.RentalAgreement;
import com.homedespot.store.service.HomeDespotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Validated
@RestController
@AllArgsConstructor
@Tag(name = "Home Despot Controller")
public class HomeDespotController {

	private final HomeDespotService homeDespotService;

	@Operation(summary = "Return a rental agreement object", description = "Return a rental agreement object")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rental agreement successfully retrieved"),
			@ApiResponse(responseCode = "400", description = "Bad Request: Data validation error"),
			@ApiResponse(responseCode = "500", description = "Server Error: this status should never appear, unless the DB is down, has been changed, or if jon-api is broken.") })
	@PostMapping("/checkout")
	public RentalAgreement checkout(@Valid @RequestBody HomeDespotRequest request) throws ParseException {
		RentalAgreement rentalAgreement = homeDespotService.checkoutService(request);
		System.out.println(rentalAgreement);
		return rentalAgreement;
	}
}
