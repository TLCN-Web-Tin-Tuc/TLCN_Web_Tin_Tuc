package com.example.demo.service;

import com.example.demo.model.WebsiteUrl;

import java.util.List;

public interface WebsiteUrlService {

    List<WebsiteUrl> retrieveAll();

    WebsiteUrl retrieveById(long id);

    WebsiteUrl retrieveByTitle(String title);
}
