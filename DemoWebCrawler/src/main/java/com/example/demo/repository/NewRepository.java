package com.example.demo.repository;

import com.example.demo.model.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewRepository extends JpaRepository<New, Long> {
    Optional<New> findByTitle(String title);
}
