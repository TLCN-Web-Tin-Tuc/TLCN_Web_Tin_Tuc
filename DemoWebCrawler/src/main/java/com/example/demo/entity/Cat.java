package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "cat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "cIId.cat")
    private Set<CatItem> catItems;

    @OneToMany(mappedBy = "id.cat")
    private Set<CatWeb> catWeb;

}
