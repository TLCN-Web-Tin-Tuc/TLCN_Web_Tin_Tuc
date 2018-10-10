package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface AccountService {

    Account findByUserNameAndPassWord(String userName, String passWord);
    //Account registerUser(Account account);
    CrudRepository<Account, Long> getRepo();
}
