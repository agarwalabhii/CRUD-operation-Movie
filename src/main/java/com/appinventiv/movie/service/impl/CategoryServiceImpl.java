package com.appinventiv.movie.service.impl;

import com.appinventiv.movie.model.Category;
import com.appinventiv.movie.repository.CategoryRepository;
import com.appinventiv.movie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setMovieRepository(@Lazy CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category movie) {
        return categoryRepository.save(movie);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.isPresent() ? optionalCategory.get() : null;
    }

    @Override
    @Transactional
    @Modifying
    public void  deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }
}
