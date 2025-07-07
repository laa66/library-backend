package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Category;
import com.laa66.librarybackend.persistence.CategoryPersistence;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryPersistence categoryPersistence;

    @Override
    public List<Category> findAll() {
        return categoryPersistence.findAll();
    }

    @Override
    public Optional<Category> findByID(long id) {
        return categoryPersistence.findByID(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryPersistence.findByName(name);
    }

    @Override
    public Category save(Category category) {
        return categoryPersistence.save(category);
    }

    @Override
    public void deleteByID(long id) {
        categoryPersistence.deleteByID(id);
    }
}
