package com.alejandro.validation.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.validation.entity.Transaction;
import com.alejandro.validation.exception.NoLocalizationException;
import com.alejandro.validation.service.TransactionService;

@RestController
@RequestMapping(path = "v1/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@PostMapping()
	public ResponseEntity<Transaction>createJavax(@Valid @RequestBody Transaction t) {
		try {
			return ResponseEntity.ok(this.service.create(t));
		} catch (NoLocalizationException e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Set<Transaction>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

}
