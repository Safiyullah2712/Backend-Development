package com.wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

    @NotNull
    private Integer fromUserId;

    @NotNull
    private Integer toUserId;

    @NotNull
    @Min(value = 1,
         message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank
    private String idempotencyKey;

    public TransferRequest() {
    }

    public TransferRequest(
            Integer fromUserId,
            Integer toUserId,
            Double amount,
            String idempotencyKey) {

        this.fromUserId =
                fromUserId;

        this.toUserId =
                toUserId;

        this.amount =
                amount;

        this.idempotencyKey =
                idempotencyKey;
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
}