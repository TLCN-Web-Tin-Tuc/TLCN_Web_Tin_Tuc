package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "news_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private WebsiteUrl websiteUrl;

    @OneToMany(mappedBy = "newCategory")
    private Set<New> news;

}
