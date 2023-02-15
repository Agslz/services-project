package com.egg.services.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "supplier")
public final class Supplier extends Person {

	@NotEmpty
	@OneToMany
	private List<Service> services;

	@NotEmpty
	@OneToMany
	private List<Review> reviews;

	@NotEmpty
	private List<Double> scores;

	@NotEmpty
	private boolean state;

	@NotEmpty
	private String biography;

	public Supplier() {
		super();
		rol = Rol.SUPPLIER;
	}

	public Supplier(int id, String name, String lastname, String phoneNumber, String mail, String image,
			String password) {
		super(id, name, lastname, phoneNumber, mail, image, password, Rol.SUPPLIER);
	}

}
