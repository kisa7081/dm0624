package com.homedespot.store.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HomeDespotRequest {

	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yy");

	private String toolCode;

	@Positive
	private Integer rentalDayCount;

	@PositiveOrZero
	@Max(value = 100)
	private Integer discountPct;

	@Pattern(regexp = "[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{2}$", message = "Date must be in MM/dd/yy format")
	private String checkOutDate;

	public Date createCheckOutDate() throws ParseException {
		return DATE_FORMATTER.parse(checkOutDate);
	}

}
