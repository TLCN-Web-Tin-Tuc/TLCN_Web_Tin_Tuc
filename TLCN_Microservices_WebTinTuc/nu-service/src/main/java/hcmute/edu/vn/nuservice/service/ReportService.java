package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportService {

    CrudRepository<Report,Long> getRepo();
}
