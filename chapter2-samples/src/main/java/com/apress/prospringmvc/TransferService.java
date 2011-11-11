package com.apress.prospringmvc;

import java.math.BigDecimal;

public interface TransferService {

	void transfer(Account from, Account to, BigDecimal amount);
	
}
