package com.wallet.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="withdrawals")
public class Withdrawal {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long withdrawalId;

    private Integer userId;

    private Double amount;

    private String bankAccountId;

    @Enumerated(EnumType.STRING)
    private WithdrawalStatus status;

    private String idempotencyKey;
    
    @Column(nullable = false)
    private Integer retryCount = 0;

    private LocalDateTime createdAt;
    
    

    public Withdrawal() {
    }

    public Long getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(
            Long withdrawalId) {

        this.withdrawalId = withdrawalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(
            Integer userId) {

        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(
            Double amount) {

        this.amount = amount;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(
            String bankAccountId) {

        this.bankAccountId = bankAccountId;
    }

    public WithdrawalStatus getStatus() {
        return status;
    }

    public void setStatus(
            WithdrawalStatus status) {

        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(
            String idempotencyKey) {

        this.idempotencyKey = idempotencyKey;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(
            Integer retryCount) {

        this.retryCount = retryCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt = createdAt;
    }
    
    
}