package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatService {
    CrudRepository<Cat,Long> getRepo();
}
