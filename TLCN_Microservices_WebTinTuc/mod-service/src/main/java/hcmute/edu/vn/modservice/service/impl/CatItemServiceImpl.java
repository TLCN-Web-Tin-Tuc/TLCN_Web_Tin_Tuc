package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.exception.NotFoundException;
import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.repository.CatItemRepository;
import hcmute.edu.vn.modservice.service.CatItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatItemServiceImpl implements CatItemService {

    @Autowired
    CatItemRepository catItemRepository;

    @Override
    public CrudRepository<Cat_Item, Long> getRepo() {
        return catItemRepository;
    }

    @Override
    public List<Cat_Item> retrieveAllCatItem(Long id) {

        return null;
    }

    @Override
    public List<Cat_Item> retrieveAllByCatId(Long id) {

        List<Cat_Item> cat_items = catItemRepository.findById_Cat_Id(id);
        if(cat_items.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");
        return cat_items;
    }
}
