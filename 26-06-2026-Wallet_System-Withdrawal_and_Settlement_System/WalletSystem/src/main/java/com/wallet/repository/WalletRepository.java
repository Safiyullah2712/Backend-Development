package com.wallet.repository;

import com.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;

import java.util.Optional;

public interface WalletRepository
        extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserId(
            Integer userId);

    @Lock(
            LockModeType.PESSIMISTIC_WRITE)
    @Query(
            "SELECT w FROM Wallet w " +
            "WHERE w.userId = :userId")
    Optional<Wallet> findByUserIdForUpdate(
            Integer userId);
}