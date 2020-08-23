package com.appinventiv.movie.service.impl;

import com.appinventiv.movie.model.Category;
import com.appinventiv.movie.model.Movie;
import com.appinventiv.movie.repository.CategoryRepository;
import com.appinventiv.movie.repository.MovieRepository;
import com.appinventiv.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setMovieRepository(@Lazy MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setCategoryRepository(@Lazy CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Movie save(Movie movie){
        Category category = categoryRepository.findByName(movie.getCategoryType());
        if(category == null){
            movie.setCategory(createCategory(movie.getCategoryType()));
        }else {
            movie.setCategory(category);
        }
        return movieRepository.save(movie);
    }

    private Category createCategory(String categoryName) {
        if(StringUtils.isEmpty(categoryName)){
            return null;
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setDisplayName(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.isPresent() ? movieOptional.get() : null;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
