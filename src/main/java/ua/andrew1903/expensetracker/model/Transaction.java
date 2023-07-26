package ua.andrew1903.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Transaction {
    private Long id;
    private LocalDate date;
    private double amount;
    private TransactionType transactionType;
    private Category category;
}
