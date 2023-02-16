package com.egg.services.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	private boolean state;

	@NotEmpty
	private String serviceType;

	@NotEmpty
	private String description;

	public Service(@NotEmpty boolean state, @NotEmpty String serviceType, @NotEmpty String description) {
		super();
		this.state = state;
		this.serviceType = serviceType;
		this.description = description;
	}

	
}
