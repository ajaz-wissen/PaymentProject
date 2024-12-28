package com.ajaz.controllers;

import com.ajaz.dtos.InstructionRequestDto;
import com.ajaz.dtos.PaymentInstructionResponseDto;
import com.ajaz.exceptions.AccountNotFoundException;
import com.ajaz.exceptions.CurrencyMismatchException;
import com.ajaz.exceptions.InsufficientBalanceException;
import com.ajaz.models.PaymentInstruction;
import com.ajaz.services.PaymentInstructionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentInstructions")
public class PaymentInstructionController {

    private PaymentInstructionService paymentInstructionService;

    public PaymentInstructionController(PaymentInstructionService paymentInstructionService){
        this.paymentInstructionService = paymentInstructionService;
    }

    @PostMapping()
    public ResponseEntity<PaymentInstruction> createPaymentInstruction(@RequestBody PaymentInstruction paymentInstruction){
        PaymentInstruction response = paymentInstructionService.createPaymentInstruction(paymentInstruction);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PaymentInstructionResponseDto> getPaymentInstruction(@RequestBody InstructionRequestDto requestDto) throws CurrencyMismatchException, InsufficientBalanceException, AccountNotFoundException {
        PaymentInstructionResponseDto responseDto = paymentInstructionService.getPaymentInstruction(requestDto.getDebitAccountNumber(), requestDto.getCreditAccountNumber(), requestDto.getAmount());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
