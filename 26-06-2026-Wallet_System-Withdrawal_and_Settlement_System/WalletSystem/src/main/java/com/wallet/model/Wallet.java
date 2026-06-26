package com.wallet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY)
    private Long walletId;

    @Column(
            nullable = false,
            unique = true)
    private Integer userId;

    @Column(
            nullable = false)
    private Double balance;
    
    @Column(
            nullable = false)
    private Double reservedBalance = 0.0;

    public Wallet() {
    }

    public Wallet(
            Long walletId,
            Integer userId,
            Double balance) {

        this.walletId = walletId;
        this.userId = userId;
        this.balance = balance;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(
            Long walletId) {

        this.walletId = walletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(
            Integer userId) {

        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(
            Double balance) {

        this.balance = balance;
    }
    
    public Double getReservedBalance() {
        return reservedBalance;
    }
    
    public void setReservedBalance(
            Double reservedBalance) {

        this.reservedBalance = reservedBalance;
    }
}