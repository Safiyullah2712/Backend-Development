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
import com.wallet.dto.WithdrawRequest;
import com.wallet.model.OutboxEvent;
import com.wallet.model.OutboxStatus;
import com.wallet.model.Withdrawal;
import com.wallet.model.WithdrawalStatus;
import com.wallet.repository.OutboxEventRepository;
import com.wallet.repository.WithdrawalRepository;
@Service
public class WalletService {

private final WalletRepository walletRepository;

private final TransactionRepository transactionRepository;

private WithdrawalRepository withdrawalRepository;

private OutboxEventRepository outboxRepository;



public WalletService(

        WalletRepository walletRepository,

        TransactionRepository transactionRepository,

        WithdrawalRepository withdrawalRepository,

        OutboxEventRepository outboxRepository) {

    this.walletRepository = walletRepository;

    this.transactionRepository =
            transactionRepository;

    this.withdrawalRepository =
            withdrawalRepository;

    this.outboxRepository =
            outboxRepository;
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

@Transactional
public Withdrawal withdrawMoney(
        WithdrawRequest request) {

    var existing =
            withdrawalRepository.findByIdempotencyKey(
                    request.getIdempotencyKey());

    if(existing.isPresent()) {

        return existing.get();
    }

    Wallet wallet =
            walletRepository.findByUserIdForUpdate(
                    request.getUserId())
                    .orElseThrow(() ->
                            new WalletNotFoundException(
                                    "Wallet not found"));

    if(wallet.getBalance()
            < request.getAmount()) {

        throw new InsufficientBalanceException(
                "Insufficient balance");
    }

    wallet.setBalance(
            wallet.getBalance()
            - request.getAmount());

    wallet.setReservedBalance(
            wallet.getReservedBalance()
            + request.getAmount());

    walletRepository.save(wallet);

    Withdrawal withdrawal =
            new Withdrawal();

    withdrawal.setUserId(
            request.getUserId());

    withdrawal.setAmount(
            request.getAmount());

    withdrawal.setBankAccountId(
            request.getBankAccountId());

    withdrawal.setStatus(
            WithdrawalStatus.PENDING);

    withdrawal.setCreatedAt(
            java.time.LocalDateTime.now());

    withdrawal.setIdempotencyKey(
            request.getIdempotencyKey());

    Withdrawal savedWithdrawal =
            withdrawalRepository.save(
                    withdrawal);

    OutboxEvent event =
            new OutboxEvent();

    event.setEventType(
            "WITHDRAWAL");

    event.setPayload(
            String.valueOf(
                    savedWithdrawal.getWithdrawalId()));

    event.setStatus(
            OutboxStatus.PENDING);

    event.setCreatedAt(
            java.time.LocalDateTime.now());

    outboxRepository.save(
            event);

    return savedWithdrawal;
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
