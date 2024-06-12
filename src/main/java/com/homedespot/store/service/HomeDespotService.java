package com.homedespot.store.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.homedespot.store.entity.HomeDespotEntity;
import com.homedespot.store.repository.HomeDespotRepository;
import com.homedespot.store.request.HomeDespotRequest;
import com.homedespot.store.response.RentalAgreement;
import com.homedespot.store.util.DateAndDiscountUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeDespotService {

	private final HomeDespotRepository homeDespotRepository;

	public RentalAgreement checkoutService(HomeDespotRequest request) throws ParseException {
		HomeDespotEntity entity = homeDespotRepository.findByToolCode(request.getToolCode());
		return createRentalAgreement(entity, request);
	}

	private RentalAgreement createRentalAgreement(HomeDespotEntity entity, HomeDespotRequest request)
			throws ParseException {
		RentalAgreement rentalAgreement = RentalAgreement.builder().toolCode(entity.getToolCode())
				.toolType(entity.getToolType()).toolBrand(entity.getBrand()).rentalDays(request.getRentalDayCount())
				.checkOutDate(request.createCheckOutDate()).dailyRentalCharge(entity.getDailyCharge())
				.discountPct(request.getDiscountPct()).build();
		Date dueDate = DateAndDiscountUtil.calculateDueDate(request.createCheckOutDate(), request.getRentalDayCount());
		rentalAgreement.setDueDate(dueDate);
		Integer chargeDays = DateAndDiscountUtil.calculateChargeDays(request.createCheckOutDate(),
				request.getRentalDayCount(), entity.getWeekdayCharge(), entity.getWeekendCharge(),
				entity.getHolidayCharge());
		rentalAgreement.setChargeDays(chargeDays);
		Double prediscountAmount = chargeDays * entity.getDailyCharge();
		rentalAgreement.setPreDescountCharge(prediscountAmount);
		Double discountPctDouble = (double) (request.getDiscountPct() / 100.00);
		Double discountAmount = discountPctDouble * prediscountAmount;
		rentalAgreement.setDiscountAmount(discountAmount);
		rentalAgreement.setFinalCharge(prediscountAmount - discountAmount);
		return rentalAgreement;
	}

}
