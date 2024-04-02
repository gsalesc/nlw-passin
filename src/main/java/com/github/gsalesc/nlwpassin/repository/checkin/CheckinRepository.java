package com.github.gsalesc.nlwpassin.repository.checkin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gsalesc.nlwpassin.domain.CheckIn;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
	boolean existsByAttendeeId(String id);
	CheckIn findByAttendeeId(String id);
}
