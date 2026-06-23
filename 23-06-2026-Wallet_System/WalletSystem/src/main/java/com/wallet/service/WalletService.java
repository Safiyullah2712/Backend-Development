package com.wallet.service;

import com.wallet.dto.AddMoneyRequest;
import com.wallet.dto.TransferRequest;
import com.wallet.exception.InsufficientBalanceException;
import com.wallet.exception.InvalidTransferException;
import com.wallet.exception.WalletNotFoundException;
import com.wallet.model.Transaction;
import com.wallet.model.TransactionStatus;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

private final WalletRepository walletRepository;

private final TransactionRepository transactionRepository;

public WalletService(
        WalletRepository walletRepository,
        TransactionRepository transactionRepository) {

    this.walletRepository =
            walletRepository;

    this.transactionRepository =
            transactionRepository;
}

public Wallet createWallet(
        Integer userId) {

    Optional<Wallet> existing =
            walletRepository.findByUserId(
                    userId);

    if(existing.isPresent()) {

        throw new InvalidTransferException(
                "Wallet already exists");
    }

    Wallet wallet =
            new Wallet();

    wallet.setUserId(
            userId);

    wallet.setBalance(
            0.0);

    return walletRepository.save(
            wallet);
}

@Transactional
public Wallet addMoney(
        AddMoneyRequest request) {

    Wallet wallet =
            walletRepository
                    .findByUserIdForUpdate(
                            request.getUserId())
                    .orElseThrow(() ->
                            new WalletNotFoundException(
                                    "Wallet not found"));

    wallet.setBalance(
            wallet.getBalance()
                    + request.getAmount());

    return walletRepository.save(
            wallet);
}

@Transactional
public Transaction transferMoney(
        TransferRequest request) {

    Optional<Transaction> existing =
            transactionRepository
                    .findByIdempotencyKey(
                            request.getIdempotencyKey());

    if(existing.isPresent()) {

        return existing.get();
    }

    if(request.getFromUserId()
            .equals(
                    request.getToUserId())) {

        throw new InvalidTransferException(
                "Cannot transfer to yourself");
    }

    Wallet senderWallet =
            walletRepository
                    .findByUserIdForUpdate(
                            request.getFromUserId())
                    .orElseThrow(() ->
                            new WalletNotFoundException(
                                    "Sender wallet not found"));

    Wallet receiverWallet =
            walletRepository
                    .findByUserIdForUpdate(
                            request.getToUserId())
                    .orElseThrow(() ->
                            new WalletNotFoundException(
                                    "Receiver wallet not found"));

    if(senderWallet.getBalance()
            < request.getAmount()) {

        throw new InsufficientBalanceException(
                "Insufficient balance");
    }

    senderWallet.setBalance(
            senderWallet.getBalance()
                    - request.getAmount());

    receiverWallet.setBalance(
            receiverWallet.getBalance()
                    + request.getAmount());

    walletRepository.save(
            senderWallet);

    walletRepository.save(
            receiverWallet);

    Transaction transaction =
            new Transaction();

    transaction.setFromUserId(
            request.getFromUserId());

    transaction.setToUserId(
            request.getToUserId());

    transaction.setAmount(
            request.getAmount());

    transaction.setStatus(
            TransactionStatus.SUCCESS);

    transaction.setCreatedAt(
            LocalDateTime.now());

    transaction.setIdempotencyKey(
            request.getIdempotencyKey());

    return transactionRepository.save(
            transaction);
}

public Wallet getWallet(
        Integer userId) {

    return walletRepository
            .findByUserId(userId)
            .orElseThrow(() ->
                    new WalletNotFoundException(
                            "Wallet not found"));
}

public List<Transaction>
getTransactions(
        Integer userId) {

    return transactionRepository
            .findByFromUserIdOrToUserId(
                    userId,
                    userId);
}


}
