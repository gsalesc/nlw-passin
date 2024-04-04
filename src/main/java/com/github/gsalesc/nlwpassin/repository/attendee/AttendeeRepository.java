package com.github.gsalesc.nlwpassin.repository.attendee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gsalesc.nlwpassin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
	List<Attendee> findByEventId(String id);

	Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}
