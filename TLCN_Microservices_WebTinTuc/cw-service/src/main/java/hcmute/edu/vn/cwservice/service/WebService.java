package hcmute.edu.vn.cwservice.service;

import hcmute.edu.vn.cwservice.entity.Web;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebService {

    CrudRepository <Web, Long> getRepo();
    List<Web> retrieveWebAll();
    Web retieveWebByID(Long id);
    Web retrieveWebByTitle(String webtitle);

}
