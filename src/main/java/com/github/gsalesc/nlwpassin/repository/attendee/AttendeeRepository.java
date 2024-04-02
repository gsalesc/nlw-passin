package com.github.gsalesc.nlwpassin.repository.attendee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
	List<Attendee> findByGroupId(String id);
}
