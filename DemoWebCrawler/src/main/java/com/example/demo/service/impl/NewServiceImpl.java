package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.New;
import com.example.demo.repository.NewRepository;
import com.example.demo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewRepository newRepository;

    @Override
    public CrudRepository<New, Long> getRepo() {
        return newRepository;
    }

    @Override
    public List<New> retrieveAll() {
        return newRepository.findAll();
    }

    @Override
    public New retrieveById(long id) {
        Optional<New> newOptional=newRepository.findById(id);
        if(!newOptional.isPresent())
            throw new NotFoundException("New not found.");
        return newOptional.get();
    }

    @Override
    public New retrieveByTitle(String title) {
        Optional<New> newOptional=newRepository.findByTitle(title);
        if(!newOptional.isPresent())
            return null;
        return newOptional.get();
    }
}
