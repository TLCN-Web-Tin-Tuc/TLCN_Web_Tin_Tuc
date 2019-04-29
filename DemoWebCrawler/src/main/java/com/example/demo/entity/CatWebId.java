package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CatWebId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Cat cat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Web web;
}
