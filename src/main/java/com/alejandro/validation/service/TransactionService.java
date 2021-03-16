package com.alejandro.validation.service;

import java.util.Set;

import com.alejandro.validation.entity.Transaction;

public interface TransactionService {
	
	public Transaction create(Transaction t);
	public Set<Transaction> getAll();

}
