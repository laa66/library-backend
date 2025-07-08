package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class CategoryPersistenceImpl implements CategoryPersistence {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        return entityManager.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Category> findByID(long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }

    @Override
    public Optional<Category> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        cq.select(root).where(cb.equal(root.get("name"), name));
        List<Category> results = entityManager.createQuery(cq).getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Category save(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public void deleteByID(long id) {
        Optional<Category> categoryOpt = Optional.ofNullable(entityManager.find(Category.class, id));
        categoryOpt.ifPresentOrElse(entityManager::remove, () -> {
            throw new RuntimeException("Category not found");
        });
    }
}
