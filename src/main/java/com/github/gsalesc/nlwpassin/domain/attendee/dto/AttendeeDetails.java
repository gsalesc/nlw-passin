package com.github.gsalesc.nlwpassin.domain.attendee.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AttendeeDetails {
	private String name;
	
	private String email;
		
	private LocalDateTime createdAt;
	
	private LocalDateTime checkinAt;
}
