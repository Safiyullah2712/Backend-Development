package com.wallet;

import com.wallet.dto.TransferRequest;
import com.wallet.service.WalletService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class WalletConcurrencyTest {

    @Autowired
    private WalletService service;

    @Test
    void concurrentTransfers()
            throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(5);

        for(int i = 1 ; i <= 5 ; i++) {

            int count = i;

            executor.submit(() -> {

                try {

                    TransferRequest request =
                            new TransferRequest();

                    request.setFromUserId(1);

                    request.setToUserId(2);

                    request.setAmount(300.0);

                    request.setIdempotencyKey(
                            "CONCURRENT-" + count);

                    service.transferMoney(
                            request);

                    System.out.println(
                            "SUCCESS -> "
                            + count);

                } catch(Exception e) {

                    System.out.println(
                            "FAILED -> "
                            + count
                            + " : "
                            + e.getMessage());
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(
                1,
                TimeUnit.MINUTES);
    }
}