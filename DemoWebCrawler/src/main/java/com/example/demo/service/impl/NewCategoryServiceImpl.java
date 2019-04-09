package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.NewCategory;
import com.example.demo.repository.NewCategoryRepository;
import com.example.demo.service.NewCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewCategoryServiceImpl implements NewCategoryService {

    @Autowired
    NewCategoryRepository newCategoryRepository;

    @Override
    public List<NewCategory> retrieveAll() {
        return newCategoryRepository.findAll();
    }

    @Override
    public NewCategory retrieveById(long id) {
        Optional<NewCategory> newCategoryOptional=newCategoryRepository.findById(id);
        if(!newCategoryOptional.isPresent())
            throw new NotFoundException("New Category not found.");
        return newCategoryOptional.get();
    }

    @Override
    public NewCategory retrieveByTitle(String title) {
        Optional<NewCategory> newCategoryOptional=newCategoryRepository.findByTitle(title);
        if(!newCategoryOptional.isPresent())
            throw new NotFoundException("New Category not found.");
        return newCategoryOptional.get();
    }
}
