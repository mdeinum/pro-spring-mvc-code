package com.apress.prospringmvc.di.constructor;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.apress.prospringmvc.Account;
import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

	private AccountService accountService;
	
	public TransferServiceImpl(AccountService accountService) {
		super();
		this.accountService=accountService;
		
	}
	
	@Override
	public void transfer(Account from, Account to, BigDecimal amount) {
	}

}
