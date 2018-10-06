package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
