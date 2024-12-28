package com.ajaz.dtos;

import com.ajaz.models.PaymentInstruction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentInstructionResponseDto {
    private PaymentInstruction debitInstruction;
    private PaymentInstruction creditInstruction;

    public PaymentInstructionResponseDto(PaymentInstruction debitInstruction, PaymentInstruction creditInstruction) {
        this.debitInstruction = debitInstruction;
        this.creditInstruction = creditInstruction;
    }

    public PaymentInstruction getDebitInstruction() {
        return debitInstruction;
    }

    public void setDebitInstruction(PaymentInstruction debitInstruction) {
        this.debitInstruction = debitInstruction;
    }

    public PaymentInstruction getCreditInstruction() {
        return creditInstruction;
    }

    public void setCreditInstruction(PaymentInstruction creditInstruction) {
        this.creditInstruction = creditInstruction;
    }
}
