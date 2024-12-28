package com.ajaz.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentInstruction {
    private Long id;
    private String accountNumber;
    private Currency currency;
    private InstructionType instructionType;
    private Long amount;
    private String bank;
    private String branch;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
