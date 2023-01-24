package com.polarbookshop.orderservice.order.web;

import com.polarbookshop.orderservice.order.domain.Order;
import com.polarbookshop.orderservice.order.domain.OrderService;
import com.polarbookshop.orderservice.order.domain.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(OrderController.class)
public class OrderControllerWebFluxTests {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private OrderService orderService;

    @Test
    void whenBookNotAvailableThenRejectedOrder() {
        var orderRequest = new OrderRequest("1234567890", 3);
        var expectedOrder = orderService.buildRejectedOrder(
                orderRequest.isbn(), orderRequest.quantity()
        );
        BDDMockito.given(orderService.submitOrder(
                orderRequest.isbn(), orderRequest.quantity()
        )).willReturn(Mono.just(expectedOrder));

        webClient.post().uri("/orders")
                .bodyValue(orderRequest)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Order.class).value(actualOrder -> {
                    Assertions.assertThat(actualOrder).isNotNull();
                    Assertions.assertThat(actualOrder.status())
                            .isEqualTo(OrderStatus.REJECTED);
                });
    }
}
