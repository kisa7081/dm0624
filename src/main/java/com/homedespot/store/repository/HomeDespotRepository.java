package com.homedespot.store.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.homedespot.store.entity.HomeDespotEntity;

/**
 * This is a fake repository.  A real one would be an interface that extends JpaRepository 
 * with a method like findByToolCode that Spring would generate the SQL.
 */
@Repository
public class HomeDespotRepository {

	private Map<String, HomeDespotEntity> fakeRepo = new HashMap<>();

	public HomeDespotRepository() {
		initFakeRepo();
	}

	private void initFakeRepo() {
		fakeRepo.put("CHNS", HomeDespotEntity.builder().toolCode("CHNS").toolType("Chainsaw").brand("Stihl")
				.dailyCharge(1.99).weekdayCharge(true).weekendCharge(true).holidayCharge(false).build());
		fakeRepo.put("LADW", HomeDespotEntity.builder().toolCode("LADW").toolType("Ladder").brand("Werner")
				.dailyCharge(1.49).weekdayCharge(true).weekendCharge(false).holidayCharge(true).build());
		fakeRepo.put("JAKD", HomeDespotEntity.builder().toolCode("JAKD").toolType("Jackhammer").brand("DeWalt")
				.dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());
		fakeRepo.put("JAKR", HomeDespotEntity.builder().toolCode("JAKR").toolType("Jackhammer").brand("Rigid")
				.dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());
	}

	public HomeDespotEntity findByToolCode(String toolCode) {
		return fakeRepo.get(toolCode);
	}
}
