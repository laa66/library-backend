package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistence {
    List<Category> findAll();
    Optional<Category> findByID(long id);
    Optional<Category> findByName(String name);
    Category save(Category category);
    void deleteByID(long id);
}
