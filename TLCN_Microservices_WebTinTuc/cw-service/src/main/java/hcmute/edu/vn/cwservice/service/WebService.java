package hcmute.edu.vn.cwservice.service;

import hcmute.edu.vn.cwservice.entity.Web;

import java.util.List;

public interface WebService {

    List<Web> retrieveWebAll();
    Web retieveWebByID(Long id);
}
