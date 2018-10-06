package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
