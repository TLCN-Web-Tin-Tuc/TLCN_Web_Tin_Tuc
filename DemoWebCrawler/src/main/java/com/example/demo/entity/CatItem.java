package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="cat_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatItem {
    @EmbeddedId
    private CatItemId cIId;
}
