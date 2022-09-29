package com.mindhub.homebanking2.dtos;

import java.time.LocalDate;

public class PaymentsDTO {

	private Long id;
	private String number;
	private int cvv;
	private String cardholder;
	private LocalDate thruDate;
	private String description;

	public PaymentsDTO() {
	}

	public PaymentsDTO(Long id, String number, int cvv, String cardholder, LocalDate thruDate, String description) {
		this.id = id;
		this.number = number;
		this.cvv = cvv;
		this.cardholder = cardholder;
		this.thruDate = thruDate;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public int getCvv() {
		return cvv;
	}

	public String getCardholder() {
		return cardholder;
	}

	public LocalDate getThruDate() {
		return thruDate;
	}

	public String getDescription() {
		return description;
	}
}

