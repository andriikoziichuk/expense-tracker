package ua.andrew1903.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transaction {
    private Long id;
    private LocalDate date;
    private double amount;
    private TransactionType type;
    private Long categoryId;
}
