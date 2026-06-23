package com.wallet.repository;

import com.wallet.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    Optional<Transaction>
    findByIdempotencyKey(
            String idempotencyKey);

    List<Transaction>
    findByFromUserIdOrToUserId(
            Integer fromUserId,
            Integer toUserId);
}