package hcmute.edu.vn.cwservice.service.impl;

import hcmute.edu.vn.cwservice.entity.Web;
import hcmute.edu.vn.cwservice.exception.NotFoundException;
import hcmute.edu.vn.cwservice.repository.WebRepository;
import hcmute.edu.vn.cwservice.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    WebRepository webRepository;

    @Override
    public CrudRepository<Web, Long> getRepo() {
        return webRepository;
    }

    @Override
    public List<Web> retrieveWebAll() {
        return webRepository.findAll();
    }

    @Override
    public Web retieveWebByID(Long id) {
        return webRepository.findById(id).get();
    }

    @Override
    public Web retrieveWebByTitle(String webtitle) {
        Optional<Web> web = webRepository.findWebByTitle(webtitle);
        if(!web.isPresent()){
            throw new NotFoundException("Không tìm thấy title");
        }
        return web.get();
    }

}
