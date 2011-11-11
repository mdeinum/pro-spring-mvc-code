package com.apress.prospringmvc.di.annotation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.prospringmvc.Account;
import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private AccountService accountService;
	
	public TransferServiceImpl() {
		super();
	}
	
	
	@Override
	public void transfer(Account from, Account to, BigDecimal amount) {
	}

}
