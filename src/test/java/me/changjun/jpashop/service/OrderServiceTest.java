package me.changjun.jpashop.service;

import me.changjun.jpashop.domain.Address;
import me.changjun.jpashop.domain.Member;
import me.changjun.jpashop.domain.Order;
import me.changjun.jpashop.domain.OrderStatus;
import me.changjun.jpashop.domain.item.Book;
import me.changjun.jpashop.domain.item.Item;
import me.changjun.jpashop.exception.NotEnoughStockException;
import me.changjun.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        //given
        Member member = createMember();

        Item book = createBook("JPA", 10000, 10);

        //when
        int orderCount = 5;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다", 10000 * 5, order.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다", 5, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() {
        //given
        Member member = createMember();
        Item book = createBook("JPA", 10000, 10);
        int orderCount = 11;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("재고 수량 보족 예외가 발생해야 한다");
    }

    @Test
    public void 주문취소() {
        //given
        Member member = createMember();
        Item book = createBook("JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL이다", OrderStatus.CANCEL, order.getStatus());
        assertEquals("주문 취소시 재고가 증가 해야 한다", 10, book.getStockQuantity());
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("changjun");
        member.setAddress(new Address("서울", "은평", "123-123"));
        em.persist(member);
        return member;
    }
}