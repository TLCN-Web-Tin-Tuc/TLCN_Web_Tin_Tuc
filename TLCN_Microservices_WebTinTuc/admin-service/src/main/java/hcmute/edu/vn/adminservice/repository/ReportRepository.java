package hcmute.edu.vn.adminservice.repository;

import hcmute.edu.vn.adminservice.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

}
