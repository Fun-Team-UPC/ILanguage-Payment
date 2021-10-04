package com.edu.upc.ilanguagepayment.query.projections;

import com.edu.upc.ilanguagepayment.command.domain.PaymentStatus;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentEdited;
import com.edu.upc.ilanguagepayment.command.domain.contrats.events.PaymentRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class PaymentViewProjection {
	private final PaymentViewRepository paymentViewRepository;

	public PaymentViewProjection(PaymentViewRepository customerViewRepository) {
        this.paymentViewRepository = customerViewRepository;
    }

	@EventHandler
    public void on(PaymentRegistered event, @Timestamp Instant timestamp) {
		PaymentView paymentView = new PaymentView(event.getPaymentId(), event.getPaymentDate(), event.getDescription(), event.getCurrency(), event.getAmount(), event.getOccurredOn());
		paymentViewRepository.save(paymentView);
    }

	@EventHandler
    public void on(PaymentEdited event, @Timestamp Instant timestamp) {
		Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(event.getPaymentId());
		if (paymentViewOptional.isPresent()) {
			PaymentView paymentView = paymentViewOptional.get();
			paymentView.setPaymentDate(event.getPaymentDate());
			paymentView.setDescription(event.getDescription());
			paymentView.setCurrency(event.getCurrency());
			paymentView.setAmount(event.getAmount());
			paymentView.setUpdatedAt(event.getOccurredOn());
			paymentViewRepository.save(paymentView);
		}
    }
}
