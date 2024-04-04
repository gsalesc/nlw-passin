package com.github.gsalesc.nlwpassin.service.checkin;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;
import com.github.gsalesc.nlwpassin.domain.checkin.CheckIn;
import com.github.gsalesc.nlwpassin.repository.checkin.CheckinRepository;

@Service
public class CheckInService {

	@Autowired
	private CheckinRepository checkInRepository;
	
	public void registerCheckIn(Attendee attendee) {
		this.verifyCheckInExists(attendee);
		
		CheckIn newCheckIn = new CheckIn();
		newCheckIn.setAttendee(attendee);
		newCheckIn.setCreatedAt(LocalDateTime.now());
		
		this.checkInRepository.save(newCheckIn);
	}

	private void verifyCheckInExists(Attendee attendee) {
		boolean isCheckedIn = this.checkInRepository.existsByAttendeeId(attendee.getId());
		
		if(isCheckedIn) throw new RuntimeException("Attendee already checked in");
	}
	
	public CheckIn findByAttendeeId(String attendeeId) {
		return checkInRepository.findByAttendeeId(attendeeId);
	}
}
