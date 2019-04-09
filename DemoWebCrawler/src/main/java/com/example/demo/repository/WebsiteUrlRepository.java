package com.example.demo.repository;

import com.example.demo.model.WebsiteUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebsiteUrlRepository extends JpaRepository<WebsiteUrl, Long> {

    Optional<WebsiteUrl> findByTitle(String title);
}
