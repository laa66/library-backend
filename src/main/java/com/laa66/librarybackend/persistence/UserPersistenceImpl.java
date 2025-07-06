package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class UserPersistenceImpl implements UserPersistence {

    private final SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        return session.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<User> findByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional.ofNullable(session.get(User.class, id))
                .ifPresentOrElse(session::delete, () -> {
                    throw new RuntimeException();
                }); // TODO: create specific exception
    }

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return  user;
    }

}
