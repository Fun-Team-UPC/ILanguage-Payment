package com.edu.upc.ilanguagepayment.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryView {
    @Id
    private long paymentHistoryId;
    private String paymentId;
    private LocalDateTime paymentDate;
    private String description;
    private Currency currency;
    private float amount;
    private Instant createdAt;

    public PaymentHistoryView(String paymentId, LocalDateTime paymentDate, String description, Currency currency, float amount, Instant createdAt) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public PaymentHistoryView(PaymentHistoryView paymentHistoryView){
        this.paymentId = paymentHistoryView.getPaymentId();
        this.paymentDate = paymentHistoryView.getPaymentDate();
        this.description = paymentHistoryView.getDescription();
        this.currency = paymentHistoryView.getCurrency();
        this.amount = paymentHistoryView.getAmount();
        this.createdAt = paymentHistoryView.getCreatedAt();
    }
}
