package com.edu.upc.ilanguagepayment.command.domain;

import com.edu.upc.ilanguagepayment.command.domain.contrats.commands.EditPayment;
import com.edu.upc.ilanguagepayment.command.domain.contrats.commands.RegisterPayment;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentEdited;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentRegistered;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Payment {
    @AggregateIdentifier
    private String paymentId;
    private LocalDateTime paymentDate;
    private String description;
    private Currency currency;
    private float amount;

    public Payment(){}

    @CommandHandler
    public Payment(RegisterPayment command) {
        Instant now =Instant.now();
        apply(
                new PaymentRegistered(
                        command.getPaymentId(),
                        command.getPaymentDate(),
                        command.getDescription(),
                        command.getCurrency(),
                        command.getAmount(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditPayment command){
        Instant now =Instant.now();
        apply(
                new PaymentEdited(
                        command.getPaymentId(),
                        command.getPaymentDate(),
                        command.getDescription(),
                        command.getCurrency(),
                        command.getAmount(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(PaymentRegistered event) {
        paymentDate =event.getPaymentDate();
        description =event.getDescription();
        currency =event.getCurrency();
        amount =event.getAmount();

    }


    @EventHandler
    protected void on(PaymentEdited event) {
        description =event.getDescription();
        currency =event.getCurrency();
        amount =event.getAmount();
    }
}
