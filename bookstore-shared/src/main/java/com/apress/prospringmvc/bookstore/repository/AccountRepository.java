package com.apress.prospringmvc.bookstore.repository;

import com.apress.prospringmvc.bookstore.domain.Account;

/**
 * Repository for working with {@link Account} domain objects
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public interface AccountRepository {

    Account findByUsername(String username);

    Account findById(long id);

    Account save(Account account);

}
