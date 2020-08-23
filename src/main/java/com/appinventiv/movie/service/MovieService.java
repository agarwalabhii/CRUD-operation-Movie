package com.appinventiv.movie.service;

import com.appinventiv.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface MovieService {

    Movie save(Movie movie) throws ConstraintViolationException;

    Page<Movie> findAll(Pageable pageable);

    List<Movie> findAll();

    Movie findById(Long id);

    void deleteById(Long id);
}
