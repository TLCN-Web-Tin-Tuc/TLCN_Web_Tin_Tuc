package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatService {
    CrudRepository<Cat,Long> getRepo();
    List<Cat> retrieveAllCat();
    Cat retrieveCatById(long id);
   // List<Cat> retrieveAllByParent_id(long id);
//    List<Cat> retrieveAllByParent_idIsNull();
    Cat UpdateCategory(Cat cat);
    Cat InsertCategory(Cat cat);
    void DeleteCategory(long id);
}
