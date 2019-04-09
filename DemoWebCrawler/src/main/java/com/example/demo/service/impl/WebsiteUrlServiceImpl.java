package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.WebsiteUrl;
import com.example.demo.repository.WebsiteUrlRepository;
import com.example.demo.service.WebsiteUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteUrlServiceImpl implements WebsiteUrlService {

    @Autowired
    WebsiteUrlRepository websiteUrlRepository;

    @Override
    public List<WebsiteUrl> retrieveAll() {
        return websiteUrlRepository.findAll();
    }

    @Override
    public WebsiteUrl retrieveById(long id) {
        Optional<WebsiteUrl> websiteUrlOptional=websiteUrlRepository.findById(id);
        if(!websiteUrlOptional.isPresent())
            throw new NotFoundException("WebsiteUrl not found.");
        return websiteUrlOptional.get();
    }

    @Override
    public WebsiteUrl retrieveByTitle(String title) {
        Optional<WebsiteUrl> websiteUrlOptional=websiteUrlRepository.findByTitle(title);
        if(!websiteUrlOptional.isPresent())
            throw new NotFoundException("WebsiteUrl not found.");
        return websiteUrlOptional.get();
    }
}
