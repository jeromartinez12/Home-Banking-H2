package com.mindhub.homebanking2.dtos;

import java.time.LocalDateTime;

public class PdfDTO {
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private String clientAccount;

	public PdfDTO(){

	}

	public PdfDTO(LocalDateTime fromDate, LocalDateTime toDate, String clientAccount) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.clientAccount = clientAccount;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}
}
