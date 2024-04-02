package com.github.gsalesc.nlwpassin.domain.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventRequestDTO {
	private String title;
	
	private String detail;
	
	private Integer maximumAteendees;
}
