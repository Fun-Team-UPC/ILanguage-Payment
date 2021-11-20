package com.edu.upc.ilanguagepayment;

import com.edu.upc.ilanguagepayment.command.application.services.PaymentAplicationService;
import com.edu.upc.ilanguagepayment.command.infra.PaymentInfraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ILanguagePaymentApplicationTests {

    @MockBean
    private PaymentInfraRepository sessionInfraRepository;

    private PaymentAplicationService sessionAplicationService;

    @BeforeEach()
    public void setUp(){
        //fixture = new AggregateTestFixture<Payment>(Payment.class);
    }



}
