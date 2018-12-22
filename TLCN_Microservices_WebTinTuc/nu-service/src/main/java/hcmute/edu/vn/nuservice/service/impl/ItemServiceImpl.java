package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Items;
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
    public CrudRepository<Items, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Items retrieveItemsById(long id) {
        Optional<Items> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        return itemOptional.get();
    }

    @Override
    public List<Items> retrieveItemsDescDay() {
        return itemRepository.findAllItemsNew();
    }

    @Override
    public List<Items> retrieveItemsDescLike() {
        return itemRepository.findAllItemsNewDescLike();
    }


}
