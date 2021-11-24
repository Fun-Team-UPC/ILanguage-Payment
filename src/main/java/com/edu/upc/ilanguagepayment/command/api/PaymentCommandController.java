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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary="Save payment", description="This endpoint is for saving a new payment for Ilanguage Application", tags = {"Payments"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment registered", content = @Content(mediaType = "application/json",
                    schema = @Schema( example = "{\"paymentId\": \"c1a4dd5a-f49c-46cb-b\",\"amount\": 50.90, \"currency\": 0, \"description\" : \"Payment succesfull\", \"paymentDate\" : 2021-11-21T15:42:42.838Z}")
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Payment Not Found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = @Content())

    })

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


    @Operation(summary="Edit payment", description="This endpoind is for editing an existing payment in Ilanguage Application", tags = {"Payments"} )
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
