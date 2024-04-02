package com.github.gsalesc.nlwpassin.service.attendee;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeDetails;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeesListResponseDTO;
import com.github.gsalesc.nlwpassin.repository.attendee.AttendeeRepository;
import com.github.gsalesc.nlwpassin.repository.checkin.CheckinRepository;

@Service
public class AttendeeService {
	
	@Autowired
	private AttendeeRepository attendeeRepository;
	
	@Autowired
	private CheckinRepository checkinRepository;
	
	public List<Attendee> getAllAttendeesByEvent(String id){
		return attendeeRepository.findByGroupId(id);
	}
	
	public AttendeesListResponseDTO getEventsAttendee(String eventId) {
		List<Attendee> attendeesList = this.getAllAttendeesByEvent(eventId);
		
		List<AttendeeDetails> attendeesDetailsList = attendeesList.stream()
							.filter(attendee -> checkinRepository.existsByAttendeeId(attendee.getId()))
							.map(attendee -> {
									LocalDateTime checkedAt = checkinRepository.findByAttendeeId(attendee.getId()).getCreatedAt();
										return new AttendeeDetails(attendee.getId(), 
										attendee.getName(), attendee.getCreatedAt(),
										checkedAt);
									})     
							.toList();
							
		
		return new AttendeesListResponseDTO(attendeesDetailsList);
	}
}
