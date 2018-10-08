package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
