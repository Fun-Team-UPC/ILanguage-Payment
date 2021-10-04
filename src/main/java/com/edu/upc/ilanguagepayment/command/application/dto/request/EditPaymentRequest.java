package com.edu.upc.ilanguagepayment.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
public class EditPaymentRequest {
	private String paymentId;
	private LocalDateTime paymentDate;
	private String description;
	private Currency currency;
	private float amount;
}
