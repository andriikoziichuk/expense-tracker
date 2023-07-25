package ua.andrew1903.expensetracker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import ua.andrew1903.expensetracker.model.TransactionType;

@Getter
@Builder
@Jacksonized
public class TransactionDTO {
    private Long id;
    private double amount;
    private TransactionType type;
    private Long categoryId;
}
