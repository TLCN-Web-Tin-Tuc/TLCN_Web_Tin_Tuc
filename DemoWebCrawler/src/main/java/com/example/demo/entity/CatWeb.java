package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="cat_web")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatWeb {
    @EmbeddedId
    private CatWebId id;

    private String rssLink;
}
