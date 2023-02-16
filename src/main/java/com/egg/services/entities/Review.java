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
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	private int idClient;

	@NotEmpty
	private String content;

	@NotEmpty
	private String image;

	public Review(@NotEmpty int idClient, @NotEmpty String content, @NotEmpty String image) {
		this.idClient = idClient;
		this.content = content;
		this.image = image;
	}

	
	
}
