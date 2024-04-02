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
@Table(name="check_ins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckIn {
	@Id
	@Column(nullable =  false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="created_at", nullable =  false)
	private LocalDateTime createdAt;
	
	@JoinColumn(name="attendee_id", nullable=false)
	private Attendee attendee;
}
