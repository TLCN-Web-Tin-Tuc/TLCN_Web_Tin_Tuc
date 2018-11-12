package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.exception.Error404;
import hcmute.edu.vn.modservice.model.Items;
import hcmute.edu.vn.modservice.repository.ItemRepository;
import hcmute.edu.vn.modservice.service.ItemService;
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
    public CrudRepository<Items, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Items getItemById(long id) {
        Optional<Items> items = itemRepository.findById(id);
        if(!items.isPresent()){
            throw new Error404("Product Not Found");
        }
        return items.get();
    }

    @Override
    public List<Items> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public List<Items> getAllItemByCategory(long id) {
        return itemRepository.findByCats(id);
    }

    @Override
    public Items InsertItem(Items items) {
        return itemRepository.save(items);
    }

    @Override
    public void DeleteItem(long id) {
        Optional<Items> itemsOptional = itemRepository.findById(id);
        if(itemsOptional.isPresent()){
            Items items = itemsOptional.get();
            items.setStatus(2);
            itemRepository.save(items);
        }
    }
}
