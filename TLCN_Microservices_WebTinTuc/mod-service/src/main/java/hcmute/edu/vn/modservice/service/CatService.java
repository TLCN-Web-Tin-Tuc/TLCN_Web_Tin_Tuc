package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.nuservice.model.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatService {
    CrudRepository<Cat,Long> getRepo();
}
