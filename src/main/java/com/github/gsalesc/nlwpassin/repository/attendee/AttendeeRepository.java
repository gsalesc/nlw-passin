package com.github.gsalesc.nlwpassin.repository.attendee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gsalesc.nlwpassin.domain.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

}
