package com.github.gsalesc.nlwpassin.domain.event.dto;

import com.github.gsalesc.nlwpassin.domain.event.Event;

import lombok.Data;

@Data
public class EventResponseDTO {
	EventDetailDTO event;
	Integer attendees;
	
	public EventResponseDTO(Event event, Integer attendees){
		this.event = new EventDetailDTO(event.getId(), event.getTitle(), 
				event.getDetails(),event.getSlug(), event.getMaximumAteendees());
		this.attendees = attendees;
	}
}
