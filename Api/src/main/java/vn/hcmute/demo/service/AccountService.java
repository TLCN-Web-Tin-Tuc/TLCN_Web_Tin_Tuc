package vn.hcmute.demo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import vn.hcmute.demo.entity.Account;

@Service
public interface AccountService {
    CrudRepository<Account, Long> getRepo();
}
