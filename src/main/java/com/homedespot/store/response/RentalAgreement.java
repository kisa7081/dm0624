package com.homedespot.store.response;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class RentalAgreement {

	private static final String CRLF = "\r\n";

	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yy");

	private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();

	private static final String PCT = "%";

	private String toolCode;

	private String toolType;

	private String toolBrand;

	private Integer rentalDays;

	private Date checkOutDate;

	private Date dueDate;

	private Double dailyRentalCharge;

	private Integer chargeDays;

	private Double preDescountCharge;

	private Integer discountPct;

	private Double discountAmount;

	private Double finalCharge;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tool code: ").append(this.getToolCode()).append(CRLF);
		sb.append("Tool type: ").append(this.getToolType()).append(CRLF);
		sb.append("Tool brand: ").append(this.getToolBrand()).append(CRLF);
		sb.append("Rental days: ").append(this.getRentalDays()).append(CRLF);
		sb.append("Check out date: ").append(DATE_FORMATTER.format(this.getCheckOutDate())).append(CRLF);
		sb.append("Due date: ").append(DATE_FORMATTER.format(this.getDueDate())).append(CRLF);
		sb.append("Daily rental charge: ").append(CURRENCY_FORMATTER.format(this.getDailyRentalCharge())).append(CRLF);
		sb.append("Charge days: ").append(this.getChargeDays()).append(CRLF);
		sb.append("Pre discount charge: ").append(CURRENCY_FORMATTER.format(this.getPreDescountCharge())).append(CRLF);
		sb.append("Discount pct: ").append(this.getDiscountPct()).append(PCT).append(CRLF);
		sb.append("Discount amount: ").append(CURRENCY_FORMATTER.format(this.getDiscountAmount())).append(CRLF);
		sb.append("Final charge: ").append(CURRENCY_FORMATTER.format(this.getFinalCharge())).append(CRLF);
		return sb.toString();
	}

}
