package me.changjun.jpashop.domain.item;

import me.changjun.jpashop.exception.NotEnoughStockException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ItemTest {
    @Test
    public void addItem() {
        //given
        Item item = new Movie();
        int stockQuantity = item.getStockQuantity();
        int addedQuantity = 1;

        //when
        item.addStock(addedQuantity);

        //then
        assertEquals(stockQuantity + addedQuantity, item.getStockQuantity());
    }

    @Test
    public void minusQuantity() {
        //given
        int addedQuantity = 2;
        int removeQuantity = 1;
        Item item = new Movie();
        item.addStock(addedQuantity);
        int stockQuantity = item.getStockQuantity();

        //when
        item.removeStock(removeQuantity);

        //then
        assertEquals(stockQuantity - removeQuantity, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void minusQuantityWithNotEnoughStock() {
        //given
        int addedQuantity = 2;
        int removeQuantity = 3;
        Item item = new Movie();
        item.addStock(addedQuantity);
        item.getStockQuantity();

        //when
        item.removeStock(removeQuantity);

        //then
        fail("NotEnoughStockException 발생해야함");
    }
}