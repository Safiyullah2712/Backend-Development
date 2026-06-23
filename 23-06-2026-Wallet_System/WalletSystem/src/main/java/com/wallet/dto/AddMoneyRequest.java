package com.wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddMoneyRequest {

    @NotNull
    private Integer userId;

    @NotNull
    @Min(value = 1,
         message = "Amount must be greater than 0")
    private Double amount;

    public AddMoneyRequest() {
    }

    public AddMoneyRequest(
            Integer userId,
            Double amount) {

        this.userId = userId;
        this.amount = amount;
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
}