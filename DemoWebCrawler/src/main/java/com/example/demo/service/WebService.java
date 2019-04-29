package com.example.demo.service;

import com.example.demo.entity.Web;

import java.util.List;
import java.util.Optional;

public interface WebService {

    List<Web> retrieveWebAll();
    Web retieveWebByID(Long id);
}
