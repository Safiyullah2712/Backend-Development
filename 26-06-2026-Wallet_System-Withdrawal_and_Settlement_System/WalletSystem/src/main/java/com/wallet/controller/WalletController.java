package com.wallet.controller;

import com.wallet.dto.AddMoneyRequest;
import com.wallet.dto.TransferRequest;
import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.service.WalletService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.wallet.dto.WithdrawRequest;
import com.wallet.model.Withdrawal;
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService service;

    public WalletController(
            WalletService service) {

        this.service = service;
    }

    @PostMapping("/create/{userId}")
    public Wallet createWallet(
            @PathVariable Integer userId) {

        return service.createWallet(
                userId);
    }

    @PostMapping("/add")
    public Wallet addMoney(
            @Valid
            @RequestBody
            AddMoneyRequest request) {

        return service.addMoney(
                request);
    }

    @PostMapping("/transfer")
    public Transaction transferMoney(
            @Valid
            @RequestBody
            TransferRequest request) {

        return service.transferMoney(
                request);
    }

    @GetMapping("/{userId}")
    public Wallet getWallet(
            @PathVariable Integer userId) {

        return service.getWallet(
                userId);
    }

    @GetMapping("/transactions/{userId}")
    public List<Transaction>
    getTransactions(
            @PathVariable Integer userId) {

        return service.getTransactions(
                userId);
    }
    
    @PostMapping("/withdraw")
    public Withdrawal withdraw(

            @RequestBody
            WithdrawRequest request) {

        return service.withdrawMoney(
                request);
    }
}