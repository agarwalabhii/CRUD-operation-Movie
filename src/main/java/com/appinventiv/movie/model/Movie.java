package com.appinventiv.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Movie {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please provide title")
    private String title;

    @NotNull(message = "Please provide categoryType")
    @JsonIgnore
    @OneToOne
    private Category category;

    @Transient
    private String categoryType;


    @NotNull(message = "Please provide rating")
    @Min(0)
    @Max(5)
    private double rating;

    public String getCategoryType() {
        if(categoryType != null){
            return categoryType;
        }else if(category != null){
            return category.getDisplayName();
        }
        return null;

    }
}
