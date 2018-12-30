package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.model.Cat;
import hcmute.edu.vn.nuservice.repository.CatRepository;
import hcmute.edu.vn.nuservice.service.CatService;
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
    public List<Cat> retrieveAllCatChecked() {
        int value = 1;
        return catRepository.findCatByCheckCat(value);
    }

    @Override
    public List<Cat> retrieveAllParentCatChecked() {
        int value = 1;
        return catRepository.findCatByCheckCatAndParentId(value, Long.valueOf(0));
    }

    @Override
    public List<Cat> retrieveAllChildCatChecked(int parentid) {
        int value = 1;
        return catRepository.findCatByCheckCatAndParentId(value, Long.valueOf(parentid));
    }

}
