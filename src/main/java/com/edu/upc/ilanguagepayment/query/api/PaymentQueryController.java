package com.edu.upc.ilanguagepayment.query.api;

import com.edu.upc.ilanguagepayment.query.projections.PaymentHistoryView;
import com.edu.upc.ilanguagepayment.query.projections.PaymentHistoryViewRepository;
import com.edu.upc.ilanguagepayment.query.projections.PaymentView;
import com.edu.upc.ilanguagepayment.query.projections.PaymentViewRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Api(tags= "Payments")
public class PaymentQueryController {
    private final PaymentViewRepository paymentViewRepository;
    private final PaymentHistoryViewRepository paymentHistoryViewRepository;

    public PaymentQueryController(PaymentViewRepository paymentViewRepository,  PaymentHistoryViewRepository paymentHistoryViewRepository) {
        this.paymentViewRepository=paymentViewRepository;
        this.paymentHistoryViewRepository=paymentHistoryViewRepository;
    }

    @Operation(summary="Get all payments", description="This endpoint returns all the available payments for Ilanguage Application", tags = {"Payments"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All Payment returned", content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"paymentId\"= \"c1a4dd5a-f49c-46cb-b\",\"amount\"= 50.90, \"currency\" = \"EUR\", \"description\"= \"Payment succesfull\", \"paymentDate\" = 2021-11-21T15:42:42.838Z}")
                    )),
            @ApiResponse(responseCode = "404", description="Payment Not Found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = @Content())

    })
    @GetMapping(value="" , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentView>> getAll(){
        try {
            return new ResponseEntity<List<PaymentView>>(paymentViewRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Getpayment by id", description="This endpoint returns an specific payment by the given ID Ilanguage Application", tags = {"Payments"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Payment found", content = @Content(mediaType = "application/json",
                    schema = @Schema( example = "{\"paymentId\": \"c1a4dd5a-f49c-46cb-b\",\"amount\": 50.90, \"currency\" = 5 , \"description\" = \"Payment succesfull\", \"paymentDate\" : 2021-11-21T15:42:42.838Z}")
                    )),
            @ApiResponse(responseCode = "404", description="Payment Not Found", content = @Content())

    })
    @RequestMapping(value= "id/{id}", method = RequestMethod.GET , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentView> getById(@PathVariable(name="id") String paymentId){
        try {
            return new ResponseEntity<PaymentView>(paymentViewRepository.getById(paymentId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@Operation(summary="Get history payment by id", description="This endpoint returns the list with the history of an specific payment by the given ID Ilanguage Application", tags = {"Payment"} )
    //@ApiResponses(value = {
    //        @ApiResponse(responseCode = "200", description="Payment returned", content = @Content(mediaType = "application/json")),
    //        @ApiResponse(responseCode = "404", description="Payment Not Found", content = @Content(mediaType = "application/json"))
//
    //})
    //@RequestMapping(value= "historyid/{historyid}", method = RequestMethod.GET)
    //public ResponseEntity<List<PaymentHistoryView>> getHistoryById(@PathVariable(name="historyid") String externalToolId){
    //    try {
    //        return new ResponseEntity<List<PaymentHistoryView>>(paymentHistoryViewRepository.getHistoryByPaymentId(externalToolId), HttpStatus.OK);
    //    }
    //    catch (Exception e) {
    //        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    //}






}
