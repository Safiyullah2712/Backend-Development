package com.wallet.service;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    public boolean transferToBank(

            String account,

            Double amount,

            Long withdrawalId) {

        System.out.println();

        System.out.println(
                "Calling BANK API...");

        System.out.println(
                "Withdrawal : " +
                withdrawalId);

        System.out.println(
                "Account : " +
                account);

        System.out.println(
                "Amount : " +
                amount);

        System.out.println();

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        double random =
                Math.random();

        if(random < 0.5) {

            System.out.println(
                    "BANK TIMEOUT");

            return false;
        }

        System.out.println(
                "BANK SUCCESS");

        return true;
    }

}