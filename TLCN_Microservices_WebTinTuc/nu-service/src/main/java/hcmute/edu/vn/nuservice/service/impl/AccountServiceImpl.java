package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Account;
import hcmute.edu.vn.nuservice.model.Role;
import hcmute.edu.vn.nuservice.repository.AccountRepository;
import hcmute.edu.vn.nuservice.repository.RoleRepository;
import hcmute.edu.vn.nuservice.repository.UserRepository;
import hcmute.edu.vn.nuservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

   @Autowired
    AccountRepository accountRepository;

   @Autowired
    RoleRepository roleRepository;

   @Autowired
    UserRepository userRepository;

    @Override
    public Account findByUserNameAndPassWord(String userName, String passWord) {
        Optional<Account> account = accountRepository.findByEmailAndPassword(userName, passWord);
        if(!account.isPresent())
            throw new NotFoundException("User Not Found!!!");
        return account.get();
    }

    @Override
    public Account registerAccount(Account account) {
        account.setStatus(1);
        Role role = roleRepository.findByRname("ROLE_USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        return accountRepository.save(account);
    }

    @Override
    public CrudRepository<Account, Long> getRepo() {
        return accountRepository;
    }

    @Override
    public Account updateRegisterAccount(Account account, Long uid) {
        account.setUser(userRepository.findById(uid).get());
        return accountRepository.save(account);
    }
}
