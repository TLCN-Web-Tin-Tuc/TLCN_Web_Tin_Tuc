package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
