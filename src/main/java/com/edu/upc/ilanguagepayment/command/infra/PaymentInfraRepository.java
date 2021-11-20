package com.edu.upc.ilanguagepayment.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

@Repository
public interface PaymentInfraRepository extends JpaRepository<PaymentInfra, String> {


    @Query("SELECT s FROM PaymentInfra s WHERE s.paymentDate = ?1")
    public Optional<PaymentInfra> findByPaymentDate(LocalDateTime paymentDate);

    @Query("SELECT s FROM PaymentInfra s WHERE s.currency = ?1")
    public Optional<PaymentInfra>findByCurrency(Currency currency);

    @Query("SELECT s FROM PaymentInfra s WHERE s.amount = ?1")
    public Optional<PaymentInfra>findByAmount(Float amount);

    @Query("SELECT s FROM PaymentInfra s WHERE s.description = ?1")
    public Optional<PaymentInfra>findByDescription(String description);

    @Query("SELECT s FROM PaymentInfra s WHERE s.paymentId = ?1")
    public Optional<PaymentInfra>findSessionByPaymentId(String paymentId);
}
