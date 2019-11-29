package me.changjun.jpashop.service.query;

import lombok.Data;
import me.changjun.jpashop.domain.Address;
import me.changjun.jpashop.domain.Order;
import me.changjun.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getUsername();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(toList());
    }
}
