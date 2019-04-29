package com.example.demo.repository;

import com.example.demo.entity.Web;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebRepository extends JpaRepository<Web,Long> {
}
