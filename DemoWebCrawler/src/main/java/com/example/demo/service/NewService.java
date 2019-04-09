package com.example.demo.service;

import com.example.demo.model.New;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewService {

    CrudRepository<New,Long> getRepo();

    List<New> retrieveAll();

    New retrieveById(long id);

    New retrieveByTitle(String title);
}
