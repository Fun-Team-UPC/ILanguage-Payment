package com.edu.upc.ilanguagepayment.command.application.validators;

import com.edu.upc.ilanguagepayment.common.application.Notification;
import com.edu.upc.ilanguagepayment.command.application.dto.request.RegisterPaymentRequest;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfra;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;
import org.springframework.stereotype.Component;
//Cambiar esta dependency x la common!!
import java.util.Currency;
import java.util.Optional;

@Component
public class RegisterPaymentValidator {
    public final PaymentInfraRepository paymentInfraRepository;

    public RegisterPaymentValidator(PaymentInfraRepository paymentInfraRepository){
        this.paymentInfraRepository = paymentInfraRepository;
    }

    public Notification validate(RegisterPaymentRequest registerPaymentRequest){
        Notification notification = new Notification();
        String description = registerPaymentRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Payment description is required");
        }
        Currency currency = registerPaymentRequest.getCurrency();
        if (currency.toString().isEmpty()) {
            notification.addError("Payment currency is required");
        }
        float amount = registerPaymentRequest.getAmount();
        if (amount == 0) {
            notification.addError("Payment amount is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<PaymentInfra> paymentDescriptionOptional = paymentInfraRepository.findById(description);
        if (paymentDescriptionOptional.isPresent()) {
            notification.addError("Payment description is taken");
        }
        return notification;
    }
}
