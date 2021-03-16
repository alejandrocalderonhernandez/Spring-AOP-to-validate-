package com.alejandro.validation.repocitory;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.validation.entity.Transaction;

public interface TransactionRepocitory extends CrudRepository<Transaction, Long>{

}
