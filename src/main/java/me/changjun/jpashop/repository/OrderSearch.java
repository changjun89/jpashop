package me.changjun.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import me.changjun.jpashop.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
