package com.edu.upc.ilanguagepayment.query.projections;

import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentEdited;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class PaymentHistoryViewProjection {
    private final PaymentHistoryViewRepository paymentHistoryViewRepository;

    public PaymentHistoryViewProjection(PaymentHistoryViewRepository paymentHistoryViewRepository) {
        this.paymentHistoryViewRepository = paymentHistoryViewRepository;
    }

    @EventHandler
    public void on(PaymentRegistered event, @Timestamp Instant timestamp) {
        PaymentHistoryView paymentHistoryView = new PaymentHistoryView(event.getPaymentId(), event.getPaymentDate(), event.getDescription(), event.getCurrency(), event.getAmount(), event.getOccurredOn());
        paymentHistoryViewRepository.save(paymentHistoryView);
    }

    //@EventHandler
    //public void on(PaymentEdited event, @Timestamp Instant timestamp) {
    //    Optional<PaymentHistoryView> paymentHistoryViewOptional = paymentHistoryViewRepository.getLastByPaymentId(event.getPaymentId());
    //    if (paymentHistoryViewOptional.isPresent()) {
    //        PaymentHistoryView paymentHistoryView = paymentHistoryViewOptional.get();
    //        paymentHistoryView = new PaymentHistoryView(paymentHistoryView);
    //        paymentHistoryView.setPaymentDate(event.getPaymentDate());
    //        paymentHistoryView.setDescription(event.getDescription());
    //        paymentHistoryView.setCurrency(event.getCurrency());
    //        paymentHistoryView.setAmount(event.getAmount());
    //        paymentHistoryView.setCreatedAt(event.getOccurredOn());
    //        paymentHistoryViewRepository.save(paymentHistoryView);
    //    }
    //}
}
