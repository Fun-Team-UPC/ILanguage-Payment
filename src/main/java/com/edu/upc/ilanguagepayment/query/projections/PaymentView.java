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
	@Column()
	private LocalDateTime paymentDate;
	@Column(length=20)
	private String description;
	@Column()
	private Currency currency;
	@Column()
	private float amount;
	@Column(nullable = true)
	private Instant updatedAt;

	public PaymentView(String paymentId, LocalDateTime paymentDate, String description, Currency currency, float amount, Instant updatedAt) {
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.description = description;
		this.currency= currency;
		this.amount = amount;
		this.updatedAt = updatedAt;
	}
}
