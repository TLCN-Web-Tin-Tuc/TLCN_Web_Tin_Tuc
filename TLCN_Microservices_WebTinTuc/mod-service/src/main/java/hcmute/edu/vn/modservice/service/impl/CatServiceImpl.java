package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.repository.CatRepository;
import hcmute.edu.vn.modservice.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepository;

    @Override
    public CrudRepository<Cat, Long> getRepo() {
        return catRepository;
    }

    @Override
    public  List<Cat> retrieveAllCat() {
        return catRepository.findAll();
    }

    @Override
    public List<Cat> retrieveAllCatChecked() {
        int value = 1;
        return catRepository.findCatByCheckCat(value);
    }

    @Override
    public Cat retrieveCatById(long id) {
        return catRepository.findById(id).get();
    }

    @Override
    public Cat UpdateCategory(Cat cat) {
        Cat catUpdate = catRepository.findById(cat.getId()).get();
        catUpdate.setName(cat.getName());
        return catRepository.save(catUpdate);
    }
    
    @Override
    public Cat InsertCategory(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public void DeleteCategory(long id) {
        Cat cat = catRepository.findById(id).get();
        if(cat != null){
            cat.setCheckCat(0);
            catRepository.save(cat);
        }
    }
}
