package com.edu.upc.ilanguagepayment.query.api;

import com.edu.upc.ilanguagepayment.query.projections.PaymentViewRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Api(tags= "Payments")
public class PaymentQueryController {
    private final PaymentViewRepository paymentViewRepository;

    public PaymentQueryController(PaymentViewRepository paymentViewRepository) {this.paymentViewRepository=paymentViewRepository;}
}
