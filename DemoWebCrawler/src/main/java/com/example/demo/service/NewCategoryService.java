package com.example.demo.service;

import com.example.demo.model.NewCategory;

import java.util.List;

public interface NewCategoryService {

    List<NewCategory> retrieveAll();

    NewCategory retrieveById(long id);

    NewCategory retrieveByTitle(String title);


}
