package com.edu.upc.ilanguagepayment.command.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfra {
    @Id
    private String paymentId;
    private LocalDateTime paymentDate;
    private String description;
    private Currency currency;
    private float amount;


    public PaymentInfra(String description, String paymentId) {
        this.description=description;
        this.paymentId = paymentId;

    }
}
