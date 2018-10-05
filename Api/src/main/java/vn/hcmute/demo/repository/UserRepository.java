package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
