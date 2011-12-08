package com.apress.prospringmvc.di;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;

import com.apress.prospringmvc.Account;
import com.apress.prospringmvc.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	private EntityManager em;
	
	@Override
	public List<Account> findAccounts() {
		long start = System.currentTimeMillis();
		try {
			return em.createQuery("from Account", Account.class).getResultList();
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("Method findAccounts() took " + (end - start) + " ms.");
		}
	}

}
