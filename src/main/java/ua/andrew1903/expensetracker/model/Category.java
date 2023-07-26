package ua.andrew1903.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private CategoryType type;
}
