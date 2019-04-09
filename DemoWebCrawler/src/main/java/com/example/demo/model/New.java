package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;

    private String fileExtension;

    @Size(max=2000000)
    private String image;

    private String title;

    private int status;

    private String shortDesc;

    @Size(max=2000000)
    private String fullDesc;

    private String author;

    private String linkOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    private NewCategory newCategory;







}