package com.example.demo.service;

import com.example.demo.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> retrieveAllItems();

    Item retrieveItemsById(long id);

    Item createItem(Item item, long catId);
}
