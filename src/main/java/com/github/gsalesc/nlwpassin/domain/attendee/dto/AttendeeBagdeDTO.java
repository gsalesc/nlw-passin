package com.github.gsalesc.nlwpassin.domain.attendee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendeeBagdeDTO {
	String name;
	String email;
	String url;
	String eventId;
}
