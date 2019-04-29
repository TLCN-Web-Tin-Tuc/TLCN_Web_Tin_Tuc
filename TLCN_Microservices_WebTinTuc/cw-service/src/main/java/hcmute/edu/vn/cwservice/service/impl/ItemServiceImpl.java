package hcmute.edu.vn.cwservice.service.impl;

import hcmute.edu.vn.cwservice.entity.Cat;
import hcmute.edu.vn.cwservice.entity.CatItem;
import hcmute.edu.vn.cwservice.entity.CatItemId;
import hcmute.edu.vn.cwservice.entity.Item;
import hcmute.edu.vn.cwservice.exception.NotFoundException;
import hcmute.edu.vn.cwservice.repository.CatItemRepository;
import hcmute.edu.vn.cwservice.repository.CatRepository;
import hcmute.edu.vn.cwservice.repository.ItemRepository;
import hcmute.edu.vn.cwservice.service.ItemService;
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
        item.setViews((long) 0);
        item.setLikes((long) 0);
        item.setComment((long) 0);
        item.setDownload((long) 0);
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
