package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
