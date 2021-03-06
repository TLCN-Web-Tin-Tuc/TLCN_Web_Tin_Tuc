package hcmute.edu.vn.userservice.service.impl;

import hcmute.edu.vn.userservice.model.Item;
import hcmute.edu.vn.userservice.repository.ItemRepository;
import hcmute.edu.vn.userservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public CrudRepository<Item, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Item itemDetail(long id) {
        Item items = itemRepository.findById(id).get();
        items.setViews(items.getViews()+1);
        return itemRepository.save(items);
    }

    @Override
    public Item likeItem(long id) {
        Item items = itemRepository.findById(id).get();
        items.setLikes(items.getLikes()+1);
        return itemRepository.save(items);
    }

    @Override
    public Item unlikeItem(long id) {
        Item items = itemRepository.findById(id).get();
        items.setLikes(items.getLikes()- 1);
        return itemRepository.save(items);
    }

}
