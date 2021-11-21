package com.edu.upc.ilanguagepayment.command.application.validators;

import com.edu.upc.ilanguagepayment.common.application.Notification;
import com.edu.upc.ilanguagepayment.command.application.dto.request.EditPaymentRequest;
import com.edu.upc.ilanguagepayment.command.domain.Payment;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfra;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;

import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

//Cambiar esta dependency x la common!!
import java.util.Currency;
import java.util.Optional;

@Component
public class EditPaymentValidator {
    private final PaymentInfraRepository paymentInfraRepository;
    private final Repository<Payment> paymentRepository;

    public EditPaymentValidator(PaymentInfraRepository paymentInfraRepository, Repository<Payment> paymentRepository){
        this.paymentInfraRepository = paymentInfraRepository;
        this.paymentRepository = paymentRepository;
    }

    public Notification validate(EditPaymentRequest editPaymentRequest){
        Notification notification = new Notification();
        String paymentId = editPaymentRequest.getPaymentId().trim();
        if(paymentId.isEmpty()){
            notification.addError("Payment id is required");
        }
        loadPaymentAggregate(paymentId);

        String description = editPaymentRequest.getDescription().trim();
        if(description.isEmpty()){notification.addError("description is required");}
        Currency currency = editPaymentRequest.getCurrency();
        if(currency.toString().isEmpty()){notification.addError("Currency is required");}
        float amount = editPaymentRequest.getAmount();
        if(amount == 0){notification.addError("Amount is required");}


        return notification;
    }

    private void loadPaymentAggregate(String paymentId){
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            paymentRepository.load(paymentId);
            unitOfWork.commit();
        }catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        }catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }
}
