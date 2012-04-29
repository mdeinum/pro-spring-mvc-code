package com.apress.prospringmvc.moneytransfer.repository;

import com.apress.prospringmvc.moneytransfer.domain.Account;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public interface AccountRepository {

    Account find(String number);

}
