package com.edu.upc.ilanguagepayment.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentHistoryViewRepository extends JpaRepository<PaymentHistoryView, String> {
    @Query(value = "SELECT * FROM payment_history_view WHERE payment_history_id = (SELECT MAX(payment_history_id) FROM payment_history_view WHERE payment_id = :paymentId)", nativeQuery = true)
    Optional<PaymentHistoryView> getLastByPaymentId(String paymentId);

    @Query(value = "SELECT * FROM payment_history_view WHERE payment_id = :payment ORDER BY created_at", nativeQuery = true)
    List<PaymentHistoryView> getHistoryByPaymentId(String paymentId);
}
