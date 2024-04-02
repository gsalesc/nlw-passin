package com.github.gsalesc.nlwpassin.domain.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDetailDTO {

	private String id;
	
	private String title;
	
	private String detail;
	
	private String slug;
	
	private Integer maximumAteendees;
}
