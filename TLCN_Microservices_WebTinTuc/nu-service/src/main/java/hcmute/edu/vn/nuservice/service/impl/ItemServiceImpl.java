package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.model.Items;
import hcmute.edu.vn.nuservice.repository.ItemRepository;
import hcmute.edu.vn.nuservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public CrudRepository<Items, Long> getRepo() {
        return itemRepository;
    }
}
