package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "website_url")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String url;

    private String classContent;

    private String classTitle;

    private String classAvatar;

    private String classshortDesc;

    @OneToMany(mappedBy = "websiteUrl")
    private Set<NewCategory> newCategories;



}
