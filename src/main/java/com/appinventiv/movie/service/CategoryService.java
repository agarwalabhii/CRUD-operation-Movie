package com.appinventiv.movie.service;

import com.appinventiv.movie.model.Category;
import org.hibernate.exception.ConstraintViolationException;


public interface CategoryService {

    Category save(Category category) throws ConstraintViolationException;

    Category findById(Long id);

    void deleteByName(String name);
}
