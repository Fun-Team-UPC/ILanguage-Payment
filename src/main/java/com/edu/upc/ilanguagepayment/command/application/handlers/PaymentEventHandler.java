package com.edu.upc.ilanguagepayment.command.application.handlers;

import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentEdited;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentRegistered;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfra;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("paymentInfra")
public class PaymentEventHandler {
    private final PaymentInfraRepository paymentInfraRepository;

    public PaymentEventHandler(PaymentInfraRepository paymentRepository) {
        this.paymentInfraRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentRegistered event) {
        paymentInfraRepository.save(new PaymentInfra(
                event.getPaymentId(),
                event.getPaymentDate(),
                event.getDescription(),
                event.getCurrency(),
                event.getAmount()
        ));
    }

    @EventHandler
    public void on(PaymentEdited event) {
        paymentInfraRepository.save(new PaymentInfra(event.getDescription(), event.getPaymentId()));
    }
}
