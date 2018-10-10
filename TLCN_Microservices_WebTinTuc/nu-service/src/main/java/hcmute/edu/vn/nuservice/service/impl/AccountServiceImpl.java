package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Account;
import hcmute.edu.vn.nuservice.repository.AccountRepository;
import hcmute.edu.vn.nuservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {

   @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findByUserNameAndPassWord(String userName, String passWord) {
        Optional<Account> account = accountRepository.findByEmailAndPassword(userName, passWord);
        if(!account.isPresent())
            throw new NotFoundException("User Not Found!!!");
        return account.get();
    }

   /* @Override
    public Account registerUser(Account account) {
        return null;
    }*/

    @Override
    public CrudRepository<Account, Long> getRepo() {
        return accountRepository;
    }
}
