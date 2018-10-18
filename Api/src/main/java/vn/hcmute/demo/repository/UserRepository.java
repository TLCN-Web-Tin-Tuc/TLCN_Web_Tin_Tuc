package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.demo.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String s);
    Optional<User> findById(long id);
    Optional<User> findByIdOrEmail(long id, String email);
}
