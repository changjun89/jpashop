package me.changjun.jpashop.service;

import lombok.RequiredArgsConstructor;
import me.changjun.jpashop.domain.item.Item;
import me.changjun.jpashop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item find(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
