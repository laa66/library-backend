package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Book;
import com.laa66.librarybackend.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class BookPersistenceImpl implements BookPersistence {

    private final SessionFactory sessionFactory;

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        return session.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Book> findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);

        Join<Object, Object> categoryJoin = bookRoot.join("category");

        return session.createQuery(cq.select(bookRoot)
                        .where(cb.equal(categoryJoin.get("name"), categoryName)))
                .getResultList();
    }

    @Override
    public Book save(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
        return book;
    }

    @Override
    public void deleteByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional.ofNullable(session.get(Book.class, id))
                .ifPresentOrElse(session::delete, () -> {
                    throw new RuntimeException();
                }); // TODO: create specific exception
    }
}
