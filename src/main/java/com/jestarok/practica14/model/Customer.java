package com.jestarok.practica14.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Customer{

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;

	private CustomerStatus status;

	private String email = "";

	public Customer() {
	}

	public Customer(Customer customer) {
		this.birthDate = customer.birthDate;
		this.firstName = customer.firstName;
		this.lastName = customer.lastName;
		this.email = customer.email;
		this.status = customer.status;
		this.id = customer.id;
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the value of status
	 *
	 * @return the value of status
	 */
	public CustomerStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of status
	 *
	 * @param status
	 *            new value of status
	 */
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	/**
	 * Get the value of birthDate
	 *
	 * @return the value of birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * Set the value of birthDate
	 *
	 * @param birthDate
	 *            new value of birthDate
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Get the value of lastName
	 *
	 * @return the value of lastName
	 */

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id,
				firstName, lastName);
	}

}
