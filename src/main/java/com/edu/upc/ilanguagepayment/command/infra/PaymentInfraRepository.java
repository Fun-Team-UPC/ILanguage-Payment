package com.edu.upc.ilanguagepayment.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentInfraRepository extends JpaRepository<PaymentInfra, String> {

    Optional<PaymentInfra> getDescriptionByPaymentId(String paymentId);

    @Query(value = "SELECT * FROM payment_description WHERE payment_id<> :payment_id AND description = :description LIMIT 1", nativeQuery = true)
    Optional<PaymentInfra> getByDescriptionForDistinctPaymentId(String description, String paymentId);
}
