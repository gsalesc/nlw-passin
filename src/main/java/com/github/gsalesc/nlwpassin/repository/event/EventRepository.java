package com.github.gsalesc.nlwpassin.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gsalesc.nlwpassin.domain.Event;

public interface EventRepository extends JpaRepository<Event, String> {

}
