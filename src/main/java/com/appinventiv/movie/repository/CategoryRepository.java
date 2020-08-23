package com.appinventiv.movie.repository;

import com.appinventiv.movie.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String categoryType);

    void deleteByName(String name);
}
