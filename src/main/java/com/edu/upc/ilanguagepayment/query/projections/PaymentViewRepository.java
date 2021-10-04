package com.edu.upc.ilanguagepayment.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentViewRepository extends JpaRepository<PaymentView, String> {
	Optional<PaymentView> getByDescription(String description);

	@Query(value = "SELECT * FROM payment_view WHERE payment_id <> :paymentId AND description = :description", nativeQuery = true)
	Optional<PaymentView> getByPaymentIdAndDescription(String paymentId, String description);
}
