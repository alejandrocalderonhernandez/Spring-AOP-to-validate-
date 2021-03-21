package com.alejandro.validation.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.validation.annotation.EnableLocalization;
import com.alejandro.validation.annotation.Utils;
import com.alejandro.validation.entity.Transaction;
import com.alejandro.validation.repocitory.TransactionRepocitory;

@Service
@Utils
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepocitory repository;
	
	@Override
	@EnableLocalization
	public Transaction create(Transaction t) {
		return this.repository.save(t);
	}
	
	public Transaction create2(Transaction t) {
		return this.repository.save(t);
	}

	@Override
	public Set<Transaction> getAll() {
		Set<Transaction> r = new HashSet<>();
		this.repository.findAll().forEach(r::add);
		return r;
	}

}
