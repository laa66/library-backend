package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class LoanPersistenceImpl implements LoanPersistence {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Loan> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
        Root<Loan> root = cq.from(Loan.class);
        return entityManager.createQuery(cq.select(root)).getResultList();
    }

    @Override
    public Optional<Loan> findByID(long id) {
        return Optional.ofNullable(entityManager.find(Loan.class, id));
    }

    @Override
    public Loan create(Loan loan) {
        entityManager.persist(loan);
        return loan;
    }

    @Override
    public void deleteByID(long id) {
        findByID(id).ifPresentOrElse(entityManager::remove, () -> {
            throw new RuntimeException("Loan not found with id: " + id);
        });
    }

    @Override
    public void clearLoans(long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Loan> delete = cb.createCriteriaDelete(Loan.class);
        Root<Loan> root = delete.from(Loan.class);
        delete.where(cb.equal(root.get("user").get("id"), userId));
        entityManager.createQuery(delete).executeUpdate();
    }
}
