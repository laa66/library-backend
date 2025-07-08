package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class BookPersistenceImpl implements BookPersistence {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        return entityManager.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Book> findByID(Long id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);
        Join<Object, Object> categoryJoin = bookRoot.join("category");
        cq.where(cb.equal(categoryJoin.get("name"), categoryName));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Book save(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public void deleteByID(Long id) {
        Optional.ofNullable(entityManager.find(Book.class, id))
                .ifPresentOrElse(entityManager::remove, () -> {
                    throw new RuntimeException("Book not found");
                });
    }
}
