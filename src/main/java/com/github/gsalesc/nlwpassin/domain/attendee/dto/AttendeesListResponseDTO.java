package com.github.gsalesc.nlwpassin.domain.attendee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendeesListResponseDTO {
	List<AttendeeDetails> attendees;
}
