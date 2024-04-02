package com.github.gsalesc.nlwpassin.domain.event.dto;

import com.github.gsalesc.nlwpassin.domain.event.Event;

import lombok.Data;

@Data
public class EventIdDTO {
	private String id;
	
	public EventIdDTO(Event event){
		this.id = event.getId();
	}
}
