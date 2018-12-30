package hcmute.edu.vn.userservice.service.impl;

import hcmute.edu.vn.userservice.exception.NotFoundException;
import hcmute.edu.vn.userservice.model.Item;
import hcmute.edu.vn.userservice.model.ItemAccess;
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
    public CrudRepository<ItemAccess, Long> getRepo() {
        return itemAccessRepository;
    }

    @Override
    public ItemAccess userLike(long itemid, String email) {
        ItemAccess item_access = new ItemAccess();
        Item items = itemRepository.findById(itemid).get();
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        item_access.setAction((long) 1);
        item_access.setItem(items);
        item_access.setUser(user.get());
        return itemAccessRepository.save(item_access);
    }

    @Override
    public void userDisLike(long itemid, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        Optional<Item> items = itemRepository.findById(itemid);
        if(!items.isPresent())
            throw new NotFoundException("Item Not Found!!!");
        ItemAccess item_access = itemAccessRepository.findByItemAndUser(items.get(),user.get()).get();
        itemAccessRepository.delete(item_access);
    }

    @Override
    public ItemAccess findUserAndItem(long itemid, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        Optional<Item> items = itemRepository.findById(itemid);
        if(!items.isPresent())
            throw new NotFoundException("Item Not Found!!!");
        Optional<ItemAccess>  item_access = itemAccessRepository.findByItemAndUser(items.get(),user.get());
        if(!item_access.isPresent())
            throw new NotFoundException("Item Not Found!!!");
        return item_access.get();
    }

}
