package com.example.SB_SmartNetStorage.repository;

import com.example.SB_SmartNetStorage.model.Product;
import com.example.SB_SmartNetStorage.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
