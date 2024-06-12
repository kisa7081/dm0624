package com.homedespot.store.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is a fake entity.  A real one would be annotated by @Entity
 * and @Table
 */
@Component
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeDespotEntity {

	// @Id -> If this was a real entity, this would be annotated by @Id
	private String toolCode;

	private String toolType;

	private String brand;

	private Double dailyCharge;

	private Boolean weekdayCharge;

	private Boolean weekendCharge;

	private Boolean holidayCharge;
}
