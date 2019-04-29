package hcmute.edu.vn.cwservice.service.impl;

import hcmute.edu.vn.cwservice.entity.Web;
import hcmute.edu.vn.cwservice.repository.WebRepository;
import hcmute.edu.vn.cwservice.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    WebRepository webRepository;

    @Override
    public List<Web> retrieveWebAll() {
        return webRepository.findAll();
    }

    @Override
    public Web retieveWebByID(Long id) {
        return webRepository.findById(id).get();
    }


}
