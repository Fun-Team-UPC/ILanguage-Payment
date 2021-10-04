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
}
