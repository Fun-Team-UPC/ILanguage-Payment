package com.edu.upc.ilanguagepayment.command.application.dto.request;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Value
public class RegisterPaymentRequest {
	private String paymentId;
	private LocalDateTime paymentDate;
	private String description;
	private Currency currency;
	private float amount;

	public String getDescription() {
		return description;
	}

	public LocalDateTime getPaymentDate() { return paymentDate;}

	public float getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getPaymentId() {
		return paymentId;
	}

	//public void setPaymentId(String paymentId) { this.paymentId = paymentId;}

}
