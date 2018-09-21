package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.projectmanagement.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
