package com.example.demo.service.impl;

import com.example.demo.entity.Web;
import com.example.demo.repository.WebRepository;
import com.example.demo.service.WebService;
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
