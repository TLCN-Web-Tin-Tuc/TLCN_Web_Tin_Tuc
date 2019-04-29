package com.example.demo.service.impl;

import com.example.demo.entity.CatWeb;
import com.example.demo.repository.CatWebRepository;
import com.example.demo.service.CatWebService;
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
