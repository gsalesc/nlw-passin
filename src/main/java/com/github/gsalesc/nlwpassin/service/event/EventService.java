package com.github.gsalesc.nlwpassin.service.event;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeIdDTO;
import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeRequestDTO;
import com.github.gsalesc.nlwpassin.domain.event.Event;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventIdDTO;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventRequestDTO;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventResponseDTO;
import com.github.gsalesc.nlwpassin.domain.event.exception.EventFullException;
import com.github.gsalesc.nlwpassin.domain.event.exception.EventNotFoundException;
import com.github.gsalesc.nlwpassin.repository.event.EventRepository;
import com.github.gsalesc.nlwpassin.service.attendee.AttendeeService;

@Service
public class EventService {
	
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	//private AttendeeRepository attendeeRepository;
	private AttendeeService attendeeService;
	
	public EventResponseDTO getEventById(String id) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new EventNotFoundException("Event not found with id:" + id));
		
		List<Attendee> attendees = attendeeService.getAllAttendeesByEvent(id);
		
		return new EventResponseDTO(event, attendees.size());
	}
	
	public EventIdDTO createEvent(EventRequestDTO request) {
		Event newEvent = new Event();
		newEvent.setTitle(request.getTitle());
		newEvent.setDetails(request.getDetails());
		newEvent.setSlug(this.generateSlug(request.getTitle()));
		newEvent.setMaximumAteendees(request.getMaximumAttendees());
		
		eventRepository.save(newEvent);
		
		return new EventIdDTO(newEvent);
	}
	
	public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendee) {
		this.attendeeService.verifyAttendeeSubscription(eventId, attendee.getEmail());
		
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event not found with id:" + eventId));
		
		List<Attendee> attendees = attendeeService.getAllAttendeesByEvent(eventId);
		
		if(event.getMaximumAteendees() == attendees.size())
			throw new EventFullException("Event is full");
		
		Attendee newAttendee = new Attendee();
		newAttendee.setName(attendee.getEmail());
		newAttendee.setEmail(attendee.getEmail());
		newAttendee.setEvent(event);
		newAttendee.setCreatedAt(LocalDateTime.now());
		
		this.attendeeService.registerAttendee(newAttendee);
		
		return new AttendeeIdDTO(newAttendee.getId());
	}
	
	private String generateSlug(String text) {
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		
		return normalized
					//.replaceAll("[\\InCOMBINING_DIACRITICAL_MARKS]", "")
					.replaceAll("[^\\w\\s]", "")
					.replaceAll("\\s+", "-")
					.toLowerCase();
		
	}
}
