package com.edu.upc.ilanguagepayment.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentHistoryViewRepository extends JpaRepository<PaymentView, String> {
    @Query(value = "SELECT * FROM customer_history_view WHERE customer_history_id = (SELECT MAX(customer_history_id) FROM customer_history_view WHERE customer_id = :customerId)", nativeQuery = true)
    Optional<PaymentHistoryView> getLastByPaymentId(String customerId);

    @Query(value = "SELECT * FROM customer_history_view WHERE customer_id = :customerId ORDER BY created_at", nativeQuery = true)
    List<PaymentHistoryView> save(PaymentHistoryView customerId);
}
