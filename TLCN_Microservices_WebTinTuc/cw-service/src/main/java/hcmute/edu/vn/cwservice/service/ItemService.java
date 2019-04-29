package hcmute.edu.vn.cwservice.service;

import hcmute.edu.vn.cwservice.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> retrieveAllItems();

    Item retrieveItemsById(long id);

    Item createItem(Item item, long catId);
}
