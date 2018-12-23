package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatService {
    CrudRepository<Cat,Long> getRepo();
    List<Cat> retrieveAllCatChecked();
}
