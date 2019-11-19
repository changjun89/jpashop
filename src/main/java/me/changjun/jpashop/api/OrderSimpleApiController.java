package me.changjun.jpashop.api;

import lombok.RequiredArgsConstructor;
import me.changjun.jpashop.domain.Order;
import me.changjun.jpashop.repository.OrderRepository;
import me.changjun.jpashop.repository.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        return all;
    }

}
