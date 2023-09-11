package com.converter.currency.TransactionManagement.repository;


import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<PurchaseTransactionEntity, Integer> {
}
