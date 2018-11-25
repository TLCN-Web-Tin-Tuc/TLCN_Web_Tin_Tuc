package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.model.Report;
import hcmute.edu.vn.nuservice.repository.ReportRepository;
import hcmute.edu.vn.nuservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public CrudRepository<Report, Long> getRepo() {
        return reportRepository;
    }
}
