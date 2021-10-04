package com.edu.upc.ilanguagepayment.command.application.services;

import com.edu.upc.ilanguagepayment.command.application.dto.response.EditPaymentResponse;
import com.edu.upc.ilanguagepayment.command.application.validators.EditPaymentValidator;
import com.edu.upc.ilanguagepayment.command.application.validators.RegisterPaymentValidator;
import com.edu.upc.ilanguagepayment.common.application.Notification;
import com.edu.upc.ilanguagepayment.common.application.Result;
import com.edu.upc.ilanguagepayment.common.application.ResultType;
import com.edu.upc.ilanguagepayment.command.application.dto.request.EditPaymentRequest;
import com.edu.upc.ilanguagepayment.command.application.dto.request.RegisterPaymentRequest;
import com.edu.upc.ilanguagepayment.command.application.dto.response.RegisterPaymentResponse;
import com.edu.upc.ilanguagepayment.command.domain.contrats.commands.RegisterPayment;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PaymentAplicationService {

    private final RegisterPaymentValidator registerPaymentValidator;
    private final EditPaymentValidator editPaymentValidator;
    private final CommandGateway commandGateway;
    private final PaymentInfraRepository paymentInfraRepository;

    public PaymentAplicationService(RegisterPaymentValidator registerPaymentValidator, EditPaymentValidator editPaymentValidator, CommandGateway commandGateway, PaymentInfraRepository paymentInfraRepository) {
        this.registerPaymentValidator = registerPaymentValidator;
        this.editPaymentValidator = editPaymentValidator;
        this.commandGateway = commandGateway;
        this.paymentInfraRepository = paymentInfraRepository;
    }

    public Result<RegisterPaymentResponse, Notification> register(RegisterPaymentRequest registerPaymentRequest) throws Exception{
        Notification notification = this.registerPaymentValidator.validate(registerPaymentRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String paymentId = UUID.randomUUID().toString();
        RegisterPayment registerPayment = new RegisterPayment(
                paymentId,
                registerPaymentRequest.getPaymentDate(),
                registerPaymentRequest.getDescription().trim(),
                registerPaymentRequest.getCurrency(),
                registerPaymentRequest.getAmount()

        );
        CompletableFuture<Object> future = commandGateway.send(registerPayment);
        CompletableFuture<ResultType> futureResult =future.handle((ok, ex) -> (ex != null ? ResultType.FAILURE : ResultType.SUCCESS));
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterPaymentResponse registerPaymentResponseDTO = new RegisterPaymentResponse(
                registerPayment.getPaymentId(),
                registerPayment.getPaymentDate(),
                registerPayment.getDescription(),
                registerPayment.getCurrency(),
                registerPayment.getAmount()

        );
        return Result.success(registerPaymentResponseDTO);
    }

    public Result<EditPaymentResponse, Notification> edit(EditPaymentRequest editPaymentRequest) throws Exception {
        Notification notification = this.editPaymentValidator.validate(editPaymentRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        EditPaymentResponse editPayment =new EditPaymentResponse(
                editPaymentRequest.getPaymentId().trim(),
                editPaymentRequest.getPaymentDate(),
                editPaymentRequest.getDescription().trim(),
                editPaymentRequest.getCurrency(),
                editPaymentRequest.getAmount()
        );

        CompletableFuture<Object> future = commandGateway.send(editPayment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
            EditPaymentResponse editPaymentResponse = new EditPaymentResponse(
                editPayment.getPaymentId(),
                editPayment.getPaymentDate(),
                editPayment.getDescription(),
                editPayment.getCurrency(),
                editPayment.getAmount()
        );
        return Result.success(editPaymentResponse);
    }
}
