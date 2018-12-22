package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Cat_Item;
import hcmute.edu.vn.nuservice.repository.CatItemRepository;
import hcmute.edu.vn.nuservice.service.CatItemService;
import hcmute.edu.vn.nuservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatItemServiceImpl implements CatItemService {

    @Autowired
    CatItemRepository catItemRepository;

    @Autowired
    ItemService itemService;

    @Override
    public CrudRepository<Cat_Item, Long> getRepo() {
        return catItemRepository;
    }

    @Override
    public List<Cat_Item> retrieveAllCatItem(Long itemId) {
        List<Cat_Item> cat_items = catItemRepository.findCat_ItemById_Item_Id(itemId);
        if(cat_items.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");
        return cat_items;
    }

    @Override
    public List<Cat_Item> retrieveAllByCatId(Long id) {
        List<Cat_Item> cat_items = catItemRepository.findItemByCatId(id);
        if(cat_items.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");
        return cat_items;
    }

    @Override
    public List<Cat_Item> retrieveAllItemDescDay() {
        List<Cat_Item> cat_items = catItemRepository.findItemDescDay();
        if(cat_items.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");
        return cat_items;
    }
}
