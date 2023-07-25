package ua.andrew1903.expensetracker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.TransactionType;

@Getter
@Builder
@Jacksonized
public class TransactionJoinCategory {
    private Long id;
    private double amount;
    private TransactionType transactionType;
    private Long categoryId;
    private String name;
    private CategoryType categoryType;
}
