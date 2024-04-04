package com.github.gsalesc.nlwpassin.service.attendee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeBadgeResponseDTO;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeBagdeDTO;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeDetails;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeesListResponseDTO;
import com.github.gsalesc.nlwpassin.repository.attendee.AttendeeRepository;
import com.github.gsalesc.nlwpassin.repository.checkin.CheckinRepository;
import com.github.gsalesc.nlwpassin.service.checkin.CheckInService;

@Service
public class AttendeeService {
	
	@Autowired
	private AttendeeRepository attendeeRepository;
	
	@Autowired
	private CheckinRepository checkinRepository;
	
	@Autowired
	private CheckInService checkInService;
	
	public List<Attendee> getAllAttendeesByEvent(String id){
		return attendeeRepository.findByEventId(id);
	}
	
	public AttendeesListResponseDTO getEventsAttendee(String eventId) {
		List<Attendee> attendeesList = this.getAllAttendeesByEvent(eventId);
		
		List<AttendeeDetails> attendeesDetailsList = attendeesList.stream()
							.filter(attendee -> checkinRepository.existsByAttendeeId(attendee.getId()))
							.map(attendee -> {
									LocalDateTime checkedAt = checkInService.findByAttendeeId(attendee.getId()).getCreatedAt();
										return new AttendeeDetails(attendee.getId(), 
										attendee.getName(), attendee.getCreatedAt(),
										checkedAt);
									})     
							.toList();
							
		
		return new AttendeesListResponseDTO(attendeesDetailsList);
	}
	
	public void verifyAttendeeSubscription(String eventId, String email) {
		Optional<Attendee> isAttendeeSubscribed = attendeeRepository.findByEventIdAndEmail(eventId, email);
		if(isAttendeeSubscribed.isPresent()) throw new RuntimeException("Attendee already subscribed");
	}
	
	
	public Attendee registerAttendee(Attendee newAttendee) {
		return this.attendeeRepository.save(newAttendee);
	}
	
	public void checkInAttendee(String attendeeId) {
		Attendee attendee = this.getAttendeeById(attendeeId);
		this.checkInService.registerCheckIn(attendee);
	}
	
	private Attendee getAttendeeById(String attendeeId) {
		return this.attendeeRepository.findById(attendeeId)
				.orElseThrow(() -> new RuntimeException("Attendee not found"));
	}
	
	
	public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
		Attendee attendee = this.getAttendeeById(attendeeId);
		
		String uri = uriComponentsBuilder.path("attendees/{attendee-id}/checkin").buildAndExpand(attendee.getId()).toUri().toString();
		
		return new AttendeeBadgeResponseDTO(new AttendeeBagdeDTO(attendee.getName(), 
										attendee.getEmail(), uri, attendee.getEvent().getId()));
	}
}
