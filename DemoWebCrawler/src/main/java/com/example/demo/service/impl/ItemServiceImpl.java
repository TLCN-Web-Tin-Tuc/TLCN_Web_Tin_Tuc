package com.example.demo.service.impl;

import com.example.demo.entity.Cat;
import com.example.demo.entity.CatItem;
import com.example.demo.entity.CatItemId;
import com.example.demo.entity.Item;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CatItemRepository;
import com.example.demo.repository.CatRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    CatItemRepository catItemRepository;

    @Override
    public List<Item> retrieveAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item retrieveItemsById(long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        return itemOptional.get();
    }

    @Override
    public Item createItem(Item item, long catId) {
        Optional<Item> itemOptional = itemRepository.findByTitle(item.getTitle());
        Optional<Cat> catOptional = catRepository.findById(catId);
        if(item.getDecription() == null && item.getDecription() == "") {
            return null;
        }
        if (itemOptional.isPresent()) {
            return null;
        }
        if (!catOptional.isPresent()) {
            return null;
        }
        Item itemCreate = itemRepository.save(item);
        CatItem catItem = new CatItem();
        CatItemId catItemId = new CatItemId();
        catItemId.setCat(catOptional.get());
        catItemId.setItem(itemCreate);
        catItem.setCIId(catItemId);
        catItemRepository.save(catItem);
        return itemRepository.save(item);
    }
}
