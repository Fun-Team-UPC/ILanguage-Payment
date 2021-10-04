package com.edu.upc.ilanguagepayment.config;

import com.edu.upc.ilanguagepayment.command.domain.Payment;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {

    @Bean
    public Repository<Payment>eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Payment.class)
                .eventStore(eventStore)
                .build();
    }
}
