package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Item;
import hcmute.edu.vn.nuservice.repository.ItemRepository;
import hcmute.edu.vn.nuservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public CrudRepository<Item, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Item retrieveItemsById(long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        return itemOptional.get();
    }

    @Override
    public List<Item> retrieveItemsDescDay() {
        return itemRepository.findAllItemsNew();
    }

    @Override
    public List<Item> retrieveItemsDesc() {
        return itemRepository.findAllItemsNewDESC();
    }

    @Override
    public List<Item> retrieveItemsDescLike() {
        return itemRepository.findAllItemsNewDescLike();
    }

    @Override
    public Item updateView(long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        Item item = itemOptional.get();
        item.setViews(item.getViews() + 1);
        return itemRepository.save(item);
    }


}
