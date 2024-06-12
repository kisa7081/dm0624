package com.homedespot.store.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public final class DateAndDiscountUtil {

	private static final List<String> HOLIDAY_LIST = new ArrayList<>();
	private static final DateFormat HOLIDAY_FORMATTER = new SimpleDateFormat("MM-dd EEE");
	private static final DateFormat DAY_FORMATTER = new SimpleDateFormat("EEE");
	private static final String SATURDAY = "Sat";
	private static final String SUNDAY = "Sun";
	static {
		HOLIDAY_LIST.add("07-04 Mon");
		HOLIDAY_LIST.add("07-04 Tue");
		HOLIDAY_LIST.add("07-04 Wed");
		HOLIDAY_LIST.add("07-04 Thu");
		HOLIDAY_LIST.add("07-04 Fri");
		HOLIDAY_LIST.add("07-03 Fri");
		HOLIDAY_LIST.add("07-05 Mon");
		HOLIDAY_LIST.add("09-01 Mon");
		HOLIDAY_LIST.add("09-02 Mon");
		HOLIDAY_LIST.add("09-03 Mon");
		HOLIDAY_LIST.add("09-04 Mon");
		HOLIDAY_LIST.add("09-05 Mon");
		HOLIDAY_LIST.add("09-06 Mon");
		HOLIDAY_LIST.add("09-07 Mon");
	}

	private DateAndDiscountUtil() {

	}

	public static Date calculateDueDate(Date date, Integer days) {
		return addDays(date, days);
	}

	public static Integer calculateChargeDays(Date date, Integer days, Boolean weekdayCharge, Boolean weekendCharge,
			Boolean holidayCharge) {
		return (int) IntStream.range(1, days+1).filter(item -> {
			boolean isChargeDay = false;
			try {
				isChargeDay = isChargeDay(date, item, weekdayCharge, weekendCharge, holidayCharge);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return isChargeDay;
		}).count();

	}

	private static boolean isChargeDay(Date date, Integer days, Boolean weekdayCharge, Boolean weekendCharge,
			Boolean holidayCharge) throws ParseException {
		boolean isChargeDay = false;
		Date d = addDays(date, days);
		if (isHoliday(d)) {
			isChargeDay = holidayCharge;
		} else if (isWeekend(d)) {
			isChargeDay = weekendCharge;
		} else if (isWeekday(d)) {
			// While all tools are currently charged on the weekday, this test is for
			// the possibly of a tool not being charged on a weekday in the future.
			isChargeDay = weekdayCharge;
		}
		return isChargeDay;
	}

	private static Date addDays(Date date, Integer days) {
		Instant i = date.toInstant();
		Instant dueDate = i.plus(days, ChronoUnit.DAYS);
		return Date.from(dueDate);
	}

	private static boolean isHoliday(Date date) throws ParseException {
		String dateString = HOLIDAY_FORMATTER.format(date);
		return HOLIDAY_LIST.contains(dateString);
	}

	private static boolean isWeekend(Date date) {
		String day = DAY_FORMATTER.format(date);
		return day.equals(SATURDAY) || day.equals(SUNDAY);
	}

	private static boolean isWeekday(Date date) {
		return !isWeekend(date);
	}

}
