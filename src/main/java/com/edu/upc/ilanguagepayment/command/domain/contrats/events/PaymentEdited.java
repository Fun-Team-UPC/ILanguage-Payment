package com.edu.upc.ilanguagepayment.command.domain.contrats.events;

import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Currency;

@Value
public class PaymentEdited {
    String paymentId;
    LocalDateTime paymentDate;
    String description;
    Currency currency;
    float amount;
    Instant occurredOn;
}
