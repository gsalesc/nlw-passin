package com.github.gsalesc.nlwpassin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.gsalesc.nlwpassin.domain.attendee.exception.AttendeeAlreadySubscribedException;
import com.github.gsalesc.nlwpassin.domain.attendee.exception.AttendeeNotFoundException;
import com.github.gsalesc.nlwpassin.domain.event.exception.EventFullException;
import com.github.gsalesc.nlwpassin.domain.event.exception.EventNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity handleEventNotFoundException(EventNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(EventFullException.class)
	public ResponseEntity handleEventFullException(EventFullException ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(AttendeeNotFoundException.class)
	public ResponseEntity handleAttendeeNotFoundException(AttendeeNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(AttendeeAlreadySubscribedException.class)
	public ResponseEntity handleAttendeeAlreadySubscribedException(AttendeeAlreadySubscribedException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
