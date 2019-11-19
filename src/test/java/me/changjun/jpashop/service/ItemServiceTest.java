package me.changjun.jpashop.service;

import me.changjun.jpashop.domain.item.Book;
import me.changjun.jpashop.domain.item.Item;
import me.changjun.jpashop.domain.item.Movie;
import me.changjun.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void 상품_생성() {
        //given
        Item item = new Movie();
        item.setName("영화");

        //when
        itemService.saveItem(item);
        List<Item> items = itemService.findItems();

        //then
        assertEquals(1, items.size());
    }

    @Test
    public void 상품_조회() {
        //given
        Item item = new Movie();
        item.setName("영화");

        //when
        itemService.saveItem(item);
        Item savedItem = itemService.find(item.getId());

        //then
        assertNotNull(savedItem);
    }

    @Test
    public void 상품_전체조회() {
        //given
        Item movie = new Movie();
        movie.setName("영화");
        Item book = new Book();
        book.setName("JPA");

        //when
        itemService.saveItem(movie);
        itemService.saveItem(book);
        List<Item> items = itemService.findItems();

        //then
        assertEquals(2, items.size());
    }
}