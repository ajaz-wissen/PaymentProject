package com.ajaz.services;

import com.ajaz.dtos.PaymentInstructionResponseDto;
import com.ajaz.exceptions.AccountNotFoundException;
import com.ajaz.exceptions.CurrencyMismatchException;
import com.ajaz.exceptions.InsufficientBalanceException;
import com.ajaz.models.Account;
import com.ajaz.models.InstructionType;
import com.ajaz.models.PaymentInstruction;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class PaymentInstructionServiceImpl implements PaymentInstructionService{

    Map<Long, PaymentInstruction> paymentInstructionRepository = new HashMap<>();

    @Override
    public PaymentInstruction createPaymentInstruction(PaymentInstruction paymentInstruction) {
        Long id = (long) paymentInstructionRepository.size() + 1;
        paymentInstruction.setId(id);
        paymentInstructionRepository.put(id, paymentInstruction);

        return paymentInstructionRepository.get(id);
    }

    @Override
    public PaymentInstructionResponseDto getPaymentInstruction(String debitAccountNumber, String creditAccountNumber, Long amount) throws InsufficientBalanceException, CurrencyMismatchException, AccountNotFoundException {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        String accountUrl = "http://localhost:8080/accounts/accountNumber/";
        ResponseEntity<Account> responseEntityDebit = restTemplate.getForEntity(accountUrl + debitAccountNumber, Account.class, debitAccountNumber);
        ResponseEntity<Account> responseEntityCredit = restTemplate.getForEntity(accountUrl + creditAccountNumber, Account.class, creditAccountNumber);

        if(!responseEntityCredit.hasBody()){
            throw new AccountNotFoundException("Debit Account not found.");
        }

        if(!responseEntityCredit.hasBody()){
            throw new AccountNotFoundException("Credit Account not found.");
        }

        Account accountDebit = responseEntityDebit.getBody();
        Account accountCredit = responseEntityCredit.getBody();

        if(accountDebit.getCurrency() != accountCredit.getCurrency()){
            throw new CurrencyMismatchException("Credit and Debit ccount currencies did not match");
        }

        if(accountDebit.getBalance() < amount){
            String suffix = accountDebit.getAccountNumber().substring(accountDebit.getAccountNumber().length()-3, accountDebit.getAccountNumber().length());
            throw new InsufficientBalanceException("Debit account ending with " + suffix + " has insufficient balance.");
        }

        accountDebit.setBalance(accountDebit.getBalance() - amount);
        accountCredit.setBalance(accountCredit.getBalance() + amount);

        restTemplate.put(accountUrl + debitAccountNumber, accountDebit, Account.class, debitAccountNumber);
        restTemplate.put(accountUrl + creditAccountNumber, accountCredit, Account.class, creditAccountNumber);


        PaymentInstruction responseDebit = new PaymentInstruction();
        responseDebit.setId(1L);
        responseDebit.setAccountNumber(accountDebit.getAccountNumber());
        responseDebit.setCurrency(accountDebit.getCurrency());
        responseDebit.setAmount(amount);
        responseDebit.setInstructionType(InstructionType.DEBIT);
        responseDebit.setBank(accountDebit.getBank().getName());
        responseDebit.setBranch(accountDebit.getBranch());

        PaymentInstruction responseCredit = new PaymentInstruction();
        responseCredit.setId(1L);
        responseCredit.setAccountNumber(accountCredit.getAccountNumber());
        responseCredit.setCurrency(accountCredit.getCurrency());
        responseCredit.setAmount(amount);
        responseCredit.setInstructionType(InstructionType.DEBIT);
        responseCredit.setBank(accountCredit.getBank().getName());
        responseCredit.setBranch(accountCredit.getBranch());

        return new PaymentInstructionResponseDto(responseDebit, responseCredit);
    }
}
