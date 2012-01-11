package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;

/**
 * JPA implementation for the {@link BookRepository}.
 * 
 * @author Marten Deinum
 *
 */
@Repository("bookRepository")
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findById(long id) {
        return this.entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByCategory(Category category) {
        String hql = "select b from Book b where b.category=:category";
        TypedQuery<Book> query = this.entityManager.createQuery(hql, Book.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Override
    public List<Book> findRandom(int count) {
        String hql = "select b from Book b";
        TypedQuery<Book> query = this.entityManager.createQuery(hql, Book.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

}
