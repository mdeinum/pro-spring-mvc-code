package com.apress.prospringmvc.di.setter;

import java.math.BigDecimal;

import com.apress.prospringmvc.Account;
import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;

public class TransferServiceImpl implements TransferService {

	private AccountService accountService;
	
	public TransferServiceImpl() {
		super();
	}
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Override
	public void transfer(Account from, Account to, BigDecimal amount) {
	}

}
