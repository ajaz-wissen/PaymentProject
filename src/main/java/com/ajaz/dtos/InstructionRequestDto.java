package com.ajaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructionRequestDto {
    private String debitAccountNumber;
    private String creditAccountNumber;
    private Long amount;
}
