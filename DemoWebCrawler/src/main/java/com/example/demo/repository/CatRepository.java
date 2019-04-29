package com.example.demo.repository;

import com.example.demo.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat,Long> {
    Optional<Cat> findById(long id);
//    List<Cat> findCatByParent_id(long id);
    //List<Cat> findAllByParent_idIsNull();

}
