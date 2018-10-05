package vn.hcmute.demo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import vn.hcmute.demo.entity.User;

@Service
public interface UserService {
    CrudRepository<User, Long> getRepo();
}
