package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Report;
import hcmute.edu.vn.adminservice.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface ReportService {
    CrudRepository<Report,Long> getRepo();
}
