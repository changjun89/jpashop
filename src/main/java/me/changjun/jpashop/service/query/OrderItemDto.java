package me.changjun.jpashop.service.query;

import lombok.Data;
import me.changjun.jpashop.domain.OrderItem;

@Data
public class OrderItemDto {
    private String itemName;//상품명
    private int orderPrice;//가격
    private int count;//수량

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
