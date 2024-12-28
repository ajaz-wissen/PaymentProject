package com.ajaz.services;

import com.ajaz.dtos.PaymentInstructionResponseDto;
import com.ajaz.exceptions.CurrencyMismatchException;
import com.ajaz.exceptions.InsufficientBalanceException;
import com.ajaz.models.PaymentInstruction;

public interface PaymentInstructionService {
    PaymentInstruction createPaymentInstruction(PaymentInstruction paymentInstruction);
    PaymentInstructionResponseDto getPaymentInstruction(String debitAccountNumber, String creditAccountNumber, Long amount) throws InsufficientBalanceException, CurrencyMismatchException;
}
