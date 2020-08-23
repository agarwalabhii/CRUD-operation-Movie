package com.appinventiv.movie.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String displayName;

}
