package hcmute.edu.vn.userservice.service.impl;

import hcmute.edu.vn.userservice.exception.NotFoundException;
import hcmute.edu.vn.userservice.model.Item_Access;
import hcmute.edu.vn.userservice.model.Items;
import hcmute.edu.vn.userservice.model.User;
import hcmute.edu.vn.userservice.repository.ItemAccessRepository;
import hcmute.edu.vn.userservice.repository.ItemRepository;
import hcmute.edu.vn.userservice.repository.UserRepository;
import hcmute.edu.vn.userservice.service.ItemAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemAccessServiceImpl implements ItemAccessService {

    @Autowired
    ItemAccessRepository itemAccessRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CrudRepository<Item_Access, Long> getRepo() {
        return itemAccessRepository;
    }

    @Override
    public Item_Access userLike(long itemid, String email) {
        Item_Access item_access = new Item_Access();
        Items items = itemRepository.findById(itemid).get();
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        item_access.setAction((long) 1);
        item_access.setItem_ac(items);
        item_access.setUser_ia(user.get());
        return item_access;
    }

    @Override
    public void userDisLike(long itemid, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        Optional<Items> items = itemRepository.findById(itemid);
        if(!items.isPresent())
            throw new NotFoundException("Item Not Found!!!");
        Item_Access item_access = itemAccessRepository.findByItem_acAndUser_ia(items.get(),user.get()).get();
        itemAccessRepository.delete(item_access);
    }

}
