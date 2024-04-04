package com.github.gsalesc.nlwpassin.controller.attendee;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.gsalesc.nlwpassin.domain.attendee.dto.AttendeeBadgeResponseDTO;
import com.github.gsalesc.nlwpassin.service.attendee.AttendeeService;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {
	
	@Autowired
	private AttendeeService attendeeService;
	
	@GetMapping("{attendeeId}/badge")
	private ResponseEntity<AttendeeBadgeResponseDTO> getBagde(@PathVariable String attendeeId,
			UriComponentsBuilder uriComponentsBuilder) {
		
		AttendeeBadgeResponseDTO badge = attendeeService.getAttendeeBadge(attendeeId, 
				uriComponentsBuilder);
		
		return ResponseEntity.ok(badge);
	}
	
	@PostMapping("/{attendeeId}/check-in")
	public ResponseEntity registerCheckIn(@PathVariable String attendeeId,
								UriComponentsBuilder uriComponentsBuilder) {
		
		this.attendeeService.checkInAttendee(attendeeId);

		URI uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri();
									
		return ResponseEntity.created(uri).build();
	}
}
