package com.github.gsalesc.nlwpassin.controller.event;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeesListResponseDTO;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventIdDTO;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventRequestDTO;
import com.github.gsalesc.nlwpassin.domain.event.dto.EventResponseDTO;
import com.github.gsalesc.nlwpassin.service.attendee.AttendeeService;
import com.github.gsalesc.nlwpassin.service.event.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AttendeeService attendeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EventResponseDTO> getEventById(@PathVariable String id){
		return ResponseEntity.ok(this.eventService.getEventById(id));
	}
	
	@PostMapping
	public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO event,
			UriComponentsBuilder uriComponentsBuilder){
		
		EventIdDTO id = eventService.createEvent(event);
		URI uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(id.getId()).toUri();
		
		return ResponseEntity.created(uri).body(id);
	}
	
	@GetMapping("/attendees/{id}")
	public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
		AttendeesListResponseDTO attendeesList = attendeeService.getEventsAttendee(id);
		return ResponseEntity.ok(attendeesList);
	}
}
