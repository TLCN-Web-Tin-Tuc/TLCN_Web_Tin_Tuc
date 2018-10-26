package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.nuservice.model.Cat;
import hcmute.edu.vn.nuservice.repository.CatRepository;
import hcmute.edu.vn.nuservice.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepository;

    @Override
    public CrudRepository<Cat, Long> getRepo() {
        return catRepository;
    }


}
