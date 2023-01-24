package com.polarbookshop.orderservice.order.domain;

import com.polarbookshop.orderservice.PostgreSQLContainerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class OrderRepositoryR2dbcTests extends PostgreSQLContainerTestBase {
    @Autowired
    private OrderRepository orderRepository;


    @Test
    void createRejectedOrder() {
        var rejectedOrder = OrderService.buildRejectedOrder(
                "1234567890", 3
        );
        StepVerifier
                .create(orderRepository.save(rejectedOrder))
                .expectNextMatches(
                        order -> order.status().equals(OrderStatus.REJECTED)
                )
                .verifyComplete();
    }
}
