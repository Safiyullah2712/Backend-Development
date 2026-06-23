package com.wallet.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "transactions",
        uniqueConstraints = {

            @UniqueConstraint(
                    columnNames =
                    "idempotencyKey")
        }
)
public class Transaction {

    @Id
    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY)
    private Long transactionId;

    private Integer fromUserId;

    private Integer toUserId;

    private Double amount;

    @Column(
            nullable = false,
            unique = true)
    private String idempotencyKey;

    @Enumerated(
            EnumType.STRING)
    private TransactionStatus status;

    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(
            Long transactionId,
            Integer fromUserId,
            Integer toUserId,
            Double amount,
            String idempotencyKey,
            TransactionStatus status,
            LocalDateTime createdAt) {

        this.transactionId =
                transactionId;

        this.fromUserId =
                fromUserId;

        this.toUserId =
                toUserId;

        this.amount =
                amount;

        this.idempotencyKey =
                idempotencyKey;

        this.status =
                status;

        this.createdAt =
                createdAt;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(
            Long transactionId) {

        this.transactionId =
                transactionId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(
            Integer fromUserId) {

        this.fromUserId =
                fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(
            Integer toUserId) {

        this.toUserId =
                toUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(
            Double amount) {

        this.amount =
                amount;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(
            String idempotencyKey) {

        this.idempotencyKey =
                idempotencyKey;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(
            TransactionStatus status) {

        this.status =
                status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt =
                createdAt;
    }
}