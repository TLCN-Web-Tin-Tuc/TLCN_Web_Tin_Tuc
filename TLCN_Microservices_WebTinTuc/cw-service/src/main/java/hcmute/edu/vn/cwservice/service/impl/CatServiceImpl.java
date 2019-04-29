package hcmute.edu.vn.cwservice.service.impl;

import hcmute.edu.vn.cwservice.entity.Cat;
import hcmute.edu.vn.cwservice.exception.NotFoundException;
import hcmute.edu.vn.cwservice.repository.CatRepository;
import hcmute.edu.vn.cwservice.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepository;

    @Override
    public Cat retrieveCatById(long id) {
        return catRepository.findById(id).get();
    }


    @Override
    public Cat createCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat retrieveCatByName(String name) {
        Optional<Cat> catOptional = catRepository.findByName(name);
        if(!catOptional.isPresent()) {
            throw new NotFoundException("Cat Not Found");
        }
        return catOptional.get();
    }
}
