package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Category;
import com.laa66.librarybackend.entity.Loan;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class LoanPersistenceImpl implements LoanPersistence {

    private final SessionFactory sessionFactory;

    @Override
    public List<Loan> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
        Root<Loan> root = cq.from(Loan.class);
        return session.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Loan> findByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Loan loan = session.get(Loan.class, id);
        return Optional.ofNullable(loan);
    }

    @Override
    public Loan create(Loan loan) {
        sessionFactory.getCurrentSession().saveOrUpdate(loan);
        return loan;
    }

    @Override
    public void deleteByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional.ofNullable(session.get(Loan.class, id))
                .ifPresentOrElse(session::delete, () -> {
                    throw new RuntimeException();
                }); // TODO: create specific exception
    }

    @Override
    public void clearLoans(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<Loan> criteriaDelete = cb.createCriteriaDelete(Loan.class);
        Root<Loan> root = criteriaDelete.from(Loan.class);
        criteriaDelete.where(cb.equal(root.get("user").get("id"), id));
        session.createQuery(criteriaDelete).executeUpdate();
    }
}
