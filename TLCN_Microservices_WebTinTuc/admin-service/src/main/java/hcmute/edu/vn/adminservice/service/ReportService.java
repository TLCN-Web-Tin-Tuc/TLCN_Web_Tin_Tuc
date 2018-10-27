package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportService {
    CrudRepository<Report,Long> getRepo();
    List<Report> retrieveAllReport();
    void deleteReport(long id);
}
