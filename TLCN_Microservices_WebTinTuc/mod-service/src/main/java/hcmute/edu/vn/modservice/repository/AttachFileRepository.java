package hcmute.edu.vn.modservice.repository;

import hcmute.edu.vn.modservice.model.Attach_File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachFileRepository extends JpaRepository<Attach_File,Long> {
    List<Attach_File> findAllByItem(long id);
    Optional<Attach_File> findById(long id);
}
