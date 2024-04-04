package com.github.gsalesc.nlwpassin.domain.attendee.exception;

public class AttendeeAlreadySubscribedException extends RuntimeException {
	public AttendeeAlreadySubscribedException(String msg) {
		super(msg);
	}
}
