package com.github.gsalesc.nlwpassin.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="events")
@Table(name="events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	@Id
	@Column(nullable =  false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable =  false)
	private String title;
	
	@Column(nullable =  false)
	private String detail;
	
	@Column(nullable =  false, unique = true)
	private String slug;
	
	@Column(name = "maximum_attendees", nullable =  false)
	private Integer maximumAteendees;
}
