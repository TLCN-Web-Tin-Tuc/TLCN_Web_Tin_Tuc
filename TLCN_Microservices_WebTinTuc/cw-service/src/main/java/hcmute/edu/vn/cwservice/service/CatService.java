package hcmute.edu.vn.cwservice.service;

import hcmute.edu.vn.cwservice.entity.Cat;

public interface CatService {
    Cat retrieveCatById(long id);

    Cat createCat(Cat cat);

    Cat retrieveCatByName(String name);
}
