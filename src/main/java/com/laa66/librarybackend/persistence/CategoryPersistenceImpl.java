package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Book;
import com.laa66.librarybackend.entity.Category;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class CategoryPersistenceImpl implements CategoryPersistence{

    private final SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        return session.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Category> findByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> categoryRoot = cq.from(Category.class);
        cq.select(categoryRoot).where(cb.equal(categoryRoot.get("name"), name));
        List<Category> results = session.createQuery(cq).getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    @Override
    public Category save(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
        return category;
    }

    @Override
    public void deleteByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional.ofNullable(session.get(Category.class, id))
                .ifPresentOrElse(session::delete, () -> {
                    throw new RuntimeException();
                }); // TODO: create specific exception
    }
}
