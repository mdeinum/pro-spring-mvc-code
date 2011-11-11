package com.apress.prospringmvc.nodi;

import java.math.BigDecimal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.apress.prospringmvc.Account;
import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;

public class TransferServiceImpl implements TransferService {

	private AccountService accountService;
	
	public TransferServiceImpl() {
		super();
		try {
		InitialContext ic = new InitialContext();
		AccountService accountService = (AccountService) ic.lookup("accountService");
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
		
	}
	
	@Override
	public void transfer(Account from, Account to, BigDecimal amount) {
	}

}
