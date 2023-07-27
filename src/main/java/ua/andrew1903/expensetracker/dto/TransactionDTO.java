package ua.andrew1903.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import ua.andrew1903.expensetracker.service.csv.CSVSerializable;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@ToString
@Jacksonized
@JsonInclude(JsonInclude.Include. NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO implements CSVSerializable {
    private Long id;
    private double amount;
    private TransactionType transactionType;
    private LocalDate date;
    private String name;
    private CategoryType categoryType;
    private Long categoryId;

    @Override
    public String[] getHeader() {
        return new String[]{"id", "amount", "transactionType", "date"};
    }

    @Override
    public List<?> getCSVValues() {
        return List.of(this.id, this.amount, this.transactionType, this.date);
    }
}
