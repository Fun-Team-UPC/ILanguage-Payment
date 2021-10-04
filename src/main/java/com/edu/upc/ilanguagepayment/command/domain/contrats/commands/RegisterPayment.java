package com.edu.upc.ilanguagepayment.command.domain.contrats.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Value
public class RegisterPayment {
    @TargetAggregateIdentifier
    String paymentId;
    LocalDateTime paymentDate;
    String description;
    Currency currency;
    float amount;

}
