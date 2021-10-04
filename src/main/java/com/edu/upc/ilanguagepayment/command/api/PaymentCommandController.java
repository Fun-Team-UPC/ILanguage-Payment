package com.edu.upc.ilanguagepayment.command.api;

import com.edu.upc.ilanguagepayment.command.application.dto.request.EditPaymentRequest;
import com.edu.upc.ilanguagepayment.command.application.dto.request.RegisterPaymentRequest;
import com.edu.upc.ilanguagepayment.command.application.dto.response.EditPaymentResponse;
import com.edu.upc.ilanguagepayment.command.application.dto.response.RegisterPaymentResponse;
import com.edu.upc.ilanguagepayment.command.application.services.PaymentAplicationService;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;
import com.edu.upc.ilanguagepayment.common.api.ApiController;
import com.edu.upc.ilanguagepayment.common.application.Notification;
import com.edu.upc.ilanguagepayment.common.application.Result;

import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
@Api(tags = "Payments")
public class PaymentCommandController {
    private final PaymentAplicationService paymentAplicationService;
    private final CommandGateway commandGateway;
    private final PaymentInfraRepository paymentRepository;

    public PaymentCommandController(PaymentAplicationService paymentAplicationService, CommandGateway commandGateway, PaymentInfraRepository paymentRepository) {
        this.paymentAplicationService = paymentAplicationService;
        this.commandGateway = commandGateway;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterPaymentRequest registerPaymentRequest){
        try {
            Result<RegisterPaymentResponse, Notification> result = paymentAplicationService.register(registerPaymentRequest);
                if (result.isSuccess()){
                    return ApiController.created(result.getSuccess());
                }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> edit(@PathVariable("paymentId") String paymentId, @RequestBody EditPaymentRequest editPaymentRequest){
        try {
            editPaymentRequest.setPaymentId(paymentId);
            Result<EditPaymentResponse, Notification> result = paymentAplicationService.edit(editPaymentRequest);
            if (result.isSuccess()){
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        }catch (AggregateNotFoundException exception){
            return ApiController.notFound();
        }catch (Exception e){
            return ApiController.serverError();
        }
    }
}
