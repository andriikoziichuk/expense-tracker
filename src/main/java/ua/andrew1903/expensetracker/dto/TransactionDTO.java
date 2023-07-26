package ua.andrew1903.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@Jacksonized
@JsonInclude(JsonInclude.Include. NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {
    private Long id;
    private double amount;
    private TransactionType transactionType;
    private LocalDate date;
    private String name;
    private CategoryType categoryType;
    private Long categoryId;
}
