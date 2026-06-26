package com.wallet.worker;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wallet.model.OutboxEvent;
import com.wallet.model.OutboxStatus;
import com.wallet.model.Withdrawal;
import com.wallet.model.WithdrawalStatus;
import com.wallet.repository.OutboxEventRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.repository.WithdrawalRepository;
import com.wallet.service.BankService;

import jakarta.transaction.Transactional;

@Component
public class OutboxWorker {

    private final OutboxEventRepository outboxRepository;

    private final WithdrawalRepository withdrawalRepository;

    private final WalletRepository walletRepository;

    private final BankService bankService;
    
    private static final
    int MAX_RETRY = 3;

    public OutboxWorker(

            OutboxEventRepository outboxRepository,

            WithdrawalRepository withdrawalRepository,

            WalletRepository walletRepository,

            BankService bankService) {

        this.outboxRepository =
                outboxRepository;

        this.withdrawalRepository =
                withdrawalRepository;

        this.walletRepository =
                walletRepository;

        this.bankService =
                bankService;
    }

    @Scheduled(fixedDelay = 5000)

    @Transactional
    public void processEvents() {
    	
    	System.out.println();

    	System.out.println(
    	        "Checking Pending Outbox Events...");

        List<OutboxEvent> events =

                outboxRepository.findByStatus(

                        OutboxStatus.PENDING);

        for(OutboxEvent event : events) {

            Long withdrawalId =

                    Long.parseLong(

                            event.getPayload());
            
            System.out.println(
                    "Processing Event : "
                    + event.getId());

            Withdrawal withdrawal =

                    withdrawalRepository.findById(

                            withdrawalId)

                            .orElse(null);

            if(withdrawal == null) {

                continue;
            }

            withdrawal.setStatus(

                    WithdrawalStatus.PROCESSING);
            
            System.out.println(
                    "Withdrawal Status -> PROCESSING");

            withdrawalRepository.save(

                    withdrawal);
            
//            System.out.println(
//                    "Simulating Crash...");
//
//            System.exit(0);

            boolean success =

                    bankService.transferToBank(

                            withdrawal.getBankAccountId(),

                            withdrawal.getAmount(),

                            withdrawalId);

            if(success) {

                withdrawal.setStatus(
                        WithdrawalStatus.SUCCESS);

                withdrawalRepository.save(
                        withdrawal);

                var wallet =
                        walletRepository
                        .findByUserId(
                                withdrawal.getUserId())
                        .get();

                wallet.setReservedBalance(
                        wallet.getReservedBalance()
                        - withdrawal.getAmount());

                walletRepository.save(
                        wallet);

                event.setStatus(
                        OutboxStatus.COMPLETED);

                outboxRepository.save(
                        event);

                System.out.println(
                        "Withdrawal Completed");
            }
            else {

                withdrawal.setRetryCount(
                        withdrawal.getRetryCount()
                        + 1);

                if(withdrawal.getRetryCount()
                        >= MAX_RETRY) {

                    withdrawal.setStatus(
                            WithdrawalStatus.FAILED);

                    withdrawalRepository.save(
                            withdrawal);

                    var wallet =
                            walletRepository
                            .findByUserId(
                                    withdrawal.getUserId())
                            .get();

                    wallet.setBalance(
                            wallet.getBalance()
                            + withdrawal.getAmount());

                    wallet.setReservedBalance(
                            wallet.getReservedBalance()
                            - withdrawal.getAmount());

                    walletRepository.save(
                            wallet);

                    event.setStatus(
                            OutboxStatus.FAILED);

                    outboxRepository.save(
                            event);

                    System.out.println(
                            "Withdrawal Failed");

                }
                else {

                    withdrawal.setStatus(
                            WithdrawalStatus.PENDING);

                    withdrawalRepository.save(
                            withdrawal);

                    System.out.println(
                            "Retry Count = "
                            + withdrawal.getRetryCount());
                }

            }

        }

    }

}