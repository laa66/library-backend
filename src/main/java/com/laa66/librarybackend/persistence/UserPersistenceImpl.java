package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class UserPersistenceImpl implements UserPersistence {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        return entityManager.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<User> findByID(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void deleteByID(long id) {
        findByID(id).ifPresentOrElse(entityManager::remove, () -> {
            throw new RuntimeException("User not found with id: " + id);
        });
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        return user;
    }
}
