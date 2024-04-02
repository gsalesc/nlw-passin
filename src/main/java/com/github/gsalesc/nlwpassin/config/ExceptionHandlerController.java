package com.github.gsalesc.nlwpassin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.gsalesc.nlwpassin.domain.event.exception.EventNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity handleEventNotFoundException(EventNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}
