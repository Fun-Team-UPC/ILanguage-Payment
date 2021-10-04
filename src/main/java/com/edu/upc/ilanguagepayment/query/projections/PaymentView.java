package com.edu.upc.ilanguagepayment.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentView {
	@Id
	private String paymentId;
	private LocalDateTime paymentDate;
	private String description;
	private Currency currency;
	private float amount;
	private Instant updatedAt;

	public PaymentView(String paymentId, LocalDateTime paymentDate, String description, Currency currency, float amount, Instant updatedAt) {
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.description = description;
		this.currency = currency;
		this.amount = amount;
		this.updatedAt = updatedAt;
	}
}
