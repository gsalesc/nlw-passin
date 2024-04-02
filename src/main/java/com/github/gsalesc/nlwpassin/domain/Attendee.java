package com.github.gsalesc.nlwpassin.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attendees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

	@Id
	@Column(nullable =  false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable =  false)
	private String name;
	
	@Column(nullable =  false)
	private String email;
	
	@JoinColumn(name = "event_id")
	private Event event;
	
	@Column(name="created_at", nullable =  false)
	private LocalDateTime createdAt;
}
