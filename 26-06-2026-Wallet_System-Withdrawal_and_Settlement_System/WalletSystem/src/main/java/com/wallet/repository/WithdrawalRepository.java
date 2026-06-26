package com.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.model.Withdrawal;

public interface WithdrawalRepository
        extends JpaRepository<Withdrawal, Long> {

    Optional<Withdrawal>
    findByIdempotencyKey(
            String idempotencyKey);
}