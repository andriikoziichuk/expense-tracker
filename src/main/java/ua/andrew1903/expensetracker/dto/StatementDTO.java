package ua.andrew1903.expensetracker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class StatementDTO {
    private Double balance;
}
