package hcmute.edu.vn.cwservice.service.impl;

import hcmute.edu.vn.cwservice.entity.CatWeb;
import hcmute.edu.vn.cwservice.repository.CatWebRepository;
import hcmute.edu.vn.cwservice.service.CatWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatWebServiceImpl implements CatWebService {

    @Autowired
    CatWebRepository catWebRepository;

    @Override
    public List<CatWeb> retrieveAll() {
        return catWebRepository.findAll();
    }
}
