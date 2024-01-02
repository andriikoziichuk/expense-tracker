package ua.andrew1903.expensetracker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class TransactionKafkaDTO {
    private double amount;
    private TransactionType transactionType;
    private LocalDate date;
}
