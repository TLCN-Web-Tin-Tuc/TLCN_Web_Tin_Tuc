package com.example.demo.service.impl;

import com.example.demo.entity.Cat;
import com.example.demo.repository.CatRepository;
import com.example.demo.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepository;

    @Override
    public Cat retieveCatById(long id) {
        return catRepository.findById(id).get();
    }
}
