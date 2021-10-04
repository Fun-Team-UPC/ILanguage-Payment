package com.edu.upc.ilanguagepayment.command.application.dto.response;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Value
public class RegisterPaymentResponse {
    private String paymentId;
    private LocalDateTime paymentDate;
    private String description;
    private Currency currency;
    private float amount;
}
